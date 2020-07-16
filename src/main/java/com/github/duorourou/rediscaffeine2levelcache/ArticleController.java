package com.github.duorourou.rediscaffeine2levelcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/articles")
public class ArticleController {

    @Autowired
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public Flux<Article> list(@RequestParam("user") String user) {
        return Flux.fromIterable(articleService.articleList(user));
    }

    @GetMapping("/user/{name}")
    @CacheEvict(value = "articles", key = "#name")
    public void remove(@PathVariable String name) {

    }

    @GetMapping("/users")
    public void add() {
        articleService.appendNew();
    }
}
