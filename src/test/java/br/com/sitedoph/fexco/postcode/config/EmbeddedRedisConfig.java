package br.com.sitedoph.fexco.postcode.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SuppressWarnings("unused")
@Configuration
public class EmbeddedRedisConfig {

    private final Logger log = LoggerFactory.getLogger(EmbeddedRedisConfig.class);
    private RedisServer redisServer;

    @PostConstruct
    public void constructing() {
        log.info("Starting Redis");
        try {
            redisServer = new RedisServer(6379);
            redisServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        log.info("Closing redis");
        redisServer.stop();
    }
}
