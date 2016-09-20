package br.com.sitedoph.fexco.postcode.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "spring.redis", ignoreUnknownFields = false)
public class RedisProperties {

    private String host = "localhost";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
