package io.datajek.springbootapp.newsfeed.service;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.datajek.springbootapp.newsfeed.entity.Constants;
import io.datajek.springbootapp.newsfeed.entity.NewsItem;

@Component
public class NewsFetcher {

	// TODO refactor methods in this class
	public List<NewsItem> getListOfNewsItems() throws XMLStreamException, IOException {  
        
        int nItems = 0;
        List<NewsItem> listOfNewsItems = new ArrayList<NewsItem>(Constants.MAX_NUM_ITEMS);
		XMLStreamReader xmlStreamReader = null;
        
	    try {
	    	URL url = new URL(Constants.XML_URL);
			xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(url.openStream());
			
			while (xmlStreamReader.hasNext() && (nItems < Constants.MAX_NUM_ITEMS)) {
				
			    if (xmlStreamReader.next() == XMLStreamConstants.START_ELEMENT) {
			    	
			        if (xmlStreamReader.getLocalName().equals(Constants.ITEM_TAG)) {
			        	String newsItemStr = getValidItemStr(xmlStreamReader);
			        	NewsItem newsItemObj = buildItem(newsItemStr);
			        	
			        	listOfNewsItems.add(newsItemObj);
			        	
			        	nItems++;
			        }
			    }
			}
			
		} finally {
            if (xmlStreamReader != null) {
                xmlStreamReader.close();
            }
	}
	    return listOfNewsItems;
    }
	
	private String getValidItemStr(XMLStreamReader xmlStreamReader) throws XMLStreamException, JsonMappingException, JsonProcessingException {
		StringBuilder sb = new StringBuilder();
		sb.append("<" + Constants.ITEM_TAG + ">");
		
		int nextReader = 0;
		
		while (xmlStreamReader.hasNext()) {
			nextReader = xmlStreamReader.next();

		   if(nextReader == XMLStreamConstants.START_ELEMENT) {
			   String localName = xmlStreamReader.getLocalName();
			   
			   
		       if (NewsItem.getSetOfFields().contains(localName)) {
		    	   sb.append("<" + localName + ">");
		       } 
		       else {
		    	   
		    	   // skip everything until next end tag
		    	   // TODO remove extra enters (non-blocking)
		    	   Boolean stop = false;
		    	   while (xmlStreamReader.hasNext() && !stop) {
		    		   if (xmlStreamReader.next() == XMLStreamConstants.END_ELEMENT && xmlStreamReader.getLocalName().equals(localName)) {
		    			   stop = true;
		    		   }
		    	   }
		       }
		   }
		   else if(nextReader == XMLStreamConstants.END_ELEMENT) {
			   sb.append("</" + xmlStreamReader.getLocalName() + ">");
			   
			   if (xmlStreamReader.getLocalName().equals(Constants.ITEM_TAG)) {
				   break;
			   }
		   }
		   else if(xmlStreamReader.hasText()) {
			   sb.append(xmlStreamReader.getText());
		   }
		}
		
		String text = sb.toString();
		
		if (!(nextReader == XMLStreamReader.END_ELEMENT && xmlStreamReader.getLocalName().equals(Constants.ITEM_TAG))) {
			throw new InvalidParameterException("xml doesn't contain the end /item tag");
		}
    	
		return sb.toString();
	}
	
	// This one doesn't save the full description string 
	// Alternative solution: create the object directly in the method above
	private NewsItem buildItem(String itemStr) throws JsonMappingException, JsonProcessingException {
	    XmlMapper xmlMapper = new XmlMapper();
		
		return xmlMapper.readValue(itemStr, NewsItem.class);
	}
	
	public static void printList(List<NewsItem> listOfNewsItems) {
		System.out.println("\nResult: ");
		
		for (int i = 0; i < listOfNewsItems.size(); i ++) {
			System.out.println(listOfNewsItems.get(i).toString());
		}
	}

}
