package com.enigmacamp.maju_mundur.services.impl;

import com.enigmacamp.maju_mundur.services.RedisTokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisTokenBlacklistServiceImpl implements RedisTokenBlacklistService {
    private final StringRedisTemplate template;

    @Override
    public void blacklistToken(String token, Long expirationTime) {
        template.opsForValue().set(token, "blacklisted", expirationTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public Boolean isTokenBlacklisted(String token) {
        return Boolean.TRUE.equals(template.hasKey(token));
    }
}
