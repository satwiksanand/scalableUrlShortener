package com.github.satwiksanand.scalableUrlShortener.service.serviceimpl;

import com.github.satwiksanand.scalableUrlShortener.service.RateLimiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimiterServiceImpl implements RateLimiterService {

    private final StringRedisTemplate redisTemplate;

    private static final int WINDOW_SIZE_SECONDS = 60;
    private static final int MAX_REQUESTS = 1;

    public boolean isAllowed(String ipAddress) {
        String key = "rate:" + ipAddress;
        long now = System.currentTimeMillis();

        long windowStart = now - (WINDOW_SIZE_SECONDS * 1000L);

        redisTemplate.opsForZSet().removeRangeByScore(key, 0, windowStart);

        Long count = redisTemplate.opsForZSet().zCard(key);

        if (count != null && count >= MAX_REQUESTS) {
            return false;
        }

        redisTemplate.opsForZSet().add(key, String.valueOf(now), now);

        redisTemplate.expire(key, Duration.ofSeconds(WINDOW_SIZE_SECONDS));

        return true;
    }
}

