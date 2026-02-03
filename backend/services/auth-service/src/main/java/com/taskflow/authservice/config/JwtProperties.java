package com.taskflow.authservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties (
    String secret,
    Long accessTokenExpiration,
    Long refreshTokenExpiration
){}