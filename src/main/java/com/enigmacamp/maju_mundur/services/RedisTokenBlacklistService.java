package com.enigmacamp.maju_mundur.services;

public interface RedisTokenBlacklistService {
    void blacklistToken(String token, Long expirationTime);
    Boolean isTokenBlacklisted(String token);
}
