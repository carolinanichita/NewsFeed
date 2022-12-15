package io.datajek.springbootapp.newsfeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.datajek.springbootapp.newsfeed.entity.NewsItem;
import io.datajek.springbootapp.newsfeed.repository.NewsItemRepository;

@Component
public class DataBaseInteractor {
	@Autowired
	private NewsItemRepository repository;
	
	public void addToDataBase(List<NewsItem> newsItems) {
		repository.saveAll(newsItems);
	}
	
	public List<NewsItem> getLast10() {
		return repository.findAll();
	}

	public NewsItem getLast() {
		List<NewsItem> last10 = getLast10();
		return last10.get(0);
	}

	public NewsItem getById(String newsItemId) {
		return repository.getReferenceById(newsItemId);
	}

}
