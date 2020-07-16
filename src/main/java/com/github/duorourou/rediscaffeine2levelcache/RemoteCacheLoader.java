package com.github.duorourou.rediscaffeine2levelcache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Objects;

@AllArgsConstructor(staticName = "of")
public class RemoteCacheLoader implements CacheLoader<Object, Object> {
    //    private final RedisTemplate<Object, Object> redisTemplate;
    private final RedisCacheManager redisCacheManager;

    @Nullable
    @Override
    public Object load(@NonNull Object key) {
        return Objects.requireNonNull(redisCacheManager.getCache("redis")).get(key);
//        if (Optional.ofNullable(redisTemplate.hasKey(key.toString())).isPresent()) {
//            return redisTemplate.opsForValue().get(key);
    }
//        return null;
//}

}
