package com.wjs.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Wjs
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*300)
public class SessionConfig {
}
