package com.github.duorourou.rediscaffeine2levelcache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class CacheConfiguration {

    @Bean
    @Primary
    public CompositeCacheManager cacheManager(SimpleCacheManager caffeineCache,
                                              RedisCacheManager redisCacheManager) {
        CompositeCacheManager compositeCacheManager = new CustomCacheManager();
        compositeCacheManager.setCacheManagers(
                Stream.of(
                        caffeineCache,
                        redisCacheManager,
                        defaultCacheManager())
                        .collect(Collectors.toList())
        );
        return compositeCacheManager;
    }


    @Bean
    public SimpleCacheManager caffeineCache() {
        CaffeineCache caffeineCache = new CaffeineCache("articles", Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(15))
//                .writer(RemoteCacheWriter.of(redisCacheManager))
//                .build(RemoteCacheLoader.of(redisCacheManager)));
                .build());

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Collections.singleton(caffeineCache));
        return cacheManager;
    }


    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate<Object, Object> redisTemplate) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<>(Object.class)))
                .entryTtl(Duration.ofSeconds(10));
        Map<String, RedisCacheConfiguration> redisConfigurationMap = new HashMap<>();
        redisConfigurationMap.put("authors", redisCacheConfiguration);
        return new RedisCacheManager(RedisCacheWriter.lockingRedisCacheWriter(Objects.requireNonNull(redisTemplate.getConnectionFactory())),
                redisCacheConfiguration, redisConfigurationMap, false);
    }

    public CacheManager defaultCacheManager() {
        return new CaffeineCacheManager();
    }

}
