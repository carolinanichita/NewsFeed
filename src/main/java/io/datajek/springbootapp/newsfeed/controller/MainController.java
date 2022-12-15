package io.datajek.springbootapp.newsfeed.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.datajek.springbootapp.newsfeed.entity.NewsItem;
import io.datajek.springbootapp.newsfeed.service.NewsFetcher;
import io.datajek.springbootapp.newsfeed.service.DataBaseInteractor;


@Component
public class MainController {
	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private DataBaseInteractor dbInteractor;
	
	@Autowired
	private NewsFetcher newsFetcher;

	@Scheduled(fixedRate = 300000)
	public void updateDataBase() throws XMLStreamException, IOException {
		log.info("in updateDataBase()");
		
		List<NewsItem> listOfItems = newsFetcher.getListOfNewsItems();
		NewsFetcher.printList(listOfItems);
		
		dbInteractor.addToDataBase(listOfItems);
	}
	
	public List<NewsItem> getLast10() {
		List<NewsItem> last10 = dbInteractor.getLast10();
		NewsFetcher.printList(last10);
		return last10;
	}
	
	public NewsItem getLast() {
		return dbInteractor.getLast();
	}

	public NewsItem findNewsItemById(String newsItemId) {
		return dbInteractor.getById(newsItemId);
	}
}
