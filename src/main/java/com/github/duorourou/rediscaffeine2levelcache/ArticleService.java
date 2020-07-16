package com.github.duorourou.rediscaffeine2levelcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private CacheManager cacheManager;

    private List<Article> articles = new ArrayList<>(Arrays.asList(
            Article.builder().name("1").build(),
            Article.builder().name("2").build(),
            Article.builder().name("3").build(),
            Article.builder().name("4").build(),
            Article.builder().name("5").build(),
            Article.builder().name("6").build()
    ));

    @Cacheable(value = "articles", key = "#user")
    public List<Article> articleList(String user) {
        return articles;
    }

    public void appendNew() {
        this.articles.add(Article.builder().name("new one -> " + this.articles.size()).build());
    }
}
