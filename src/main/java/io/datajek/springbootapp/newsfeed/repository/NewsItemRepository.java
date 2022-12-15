package io.datajek.springbootapp.newsfeed.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import io.datajek.springbootapp.newsfeed.entity.NewsItem;

public interface NewsItemRepository extends JpaRepository<NewsItem, String>{

}
