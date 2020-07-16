package com.github.duorourou.rediscaffeine2levelcache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.lang.Nullable;

import java.util.Collections;

public class CustomCacheManager extends CompositeCacheManager {

    private final boolean allowInFlightCacheCreation = Boolean.TRUE;

    public CustomCacheManager() {
    }

    @Override
    @Nullable
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        return cache;
    }


}
