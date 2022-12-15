package io.datajek.springbootapp.newsfeed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.GraphQL;
import io.datajek.springbootapp.newsfeed.entity.NewsItem;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/newsfeed")
public class APIController {
	@Autowired
	private MainController mainController;

	@GetMapping("/last10items")
	public List<NewsItem> getLast10News() {
		return mainController.getLast10();
	}
	
	@GetMapping("/last")
	public NewsItem getLastNewsItem() {
		return mainController.getLast();
	}

	@GetMapping("/search/{newsItemId}")
	public String getNewsItemTitle(@PathVariable String newsItemId) {
		return mainController.findNewsItemById(newsItemId).getTitle();
	}
}
