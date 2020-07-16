package com.github.duorourou.rediscaffeine2levelcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Cacheable(value = "resources", key = "'res'")
    @GetMapping
    public List<String> list() {
        return Arrays.asList("res: http://x.x.x", "res: https://x.x.x");
    }
}
