package io.datajek.springbootapp.newsfeed.entity;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table
@Entity
public class NewsItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	private Date pubDate;
//	private int image;
	
	public NewsItem () {}
	
	public NewsItem (String title, String description, Date pubDate, int image) {
		super();
		this.title = title;
		this.description = description;
		this.pubDate = pubDate;
//		this.image = image;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getPubDate() {
		return pubDate;
	}
	
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	
//	public int getImage() {
//		return image;
//	}
//	
//	public void setImage(int image) {
//		this.image = image;
//	}
	
	// TODO update if we're adding another field
	public static Set<String> getSetOfFields() {
		return new HashSet<String>(Arrays.asList("title", "description", "pubDate"));
	}
	
	@Override
	public String toString() {
		return "NewsItem = [title: " + title + ", description: " + description + ", pubDate: " + pubDate + "]";
	}
}
























