package com.github.duorourou.rediscaffeine2levelcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisCaffeine2levelCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCaffeine2levelCacheApplication.class, args);
    }

}
