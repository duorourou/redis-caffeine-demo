package com.github.duorourou.rediscaffeine2levelcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {

    private final List<Author> authors = new ArrayList<>(Arrays.asList(
            Author.builder().name("Jhonh").build(),
            Author.builder().name("Smeth").build(),
            Author.builder().name("Arzzon").build()
    ));

    @GetMapping
    @Cacheable(value = "authors", key = "#name")
    public List<Author> list(@RequestParam String name) {
        return authors;
    }
}
