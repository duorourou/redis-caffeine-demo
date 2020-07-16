package com.github.duorourou.rediscaffeine2levelcache;

import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.RemovalCause;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Objects;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class RemoteCacheWriter implements CacheWriter<Object, Object> {

    private final RedisCacheManager redisCacheManager;

    @Override
    public void write(@NonNull Object key, @NonNull Object value) {
        log.debug("set a cache on redis : key -> {}, value -> {}", key, value);
        Objects.requireNonNull(redisCacheManager.getCache("redis")).put(key, value);
    }

    @Override
    public void delete(@NonNull Object key, @Nullable Object value, @NonNull RemovalCause cause) {
        log.info("delete a cache on redis : key -> {}, value -> {}, cause -> {}", key, value, cause);
        Objects.requireNonNull(redisCacheManager.getCache("redis")).evict(key);
    }
}
