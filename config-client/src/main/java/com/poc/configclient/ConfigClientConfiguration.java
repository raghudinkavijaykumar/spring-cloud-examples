package com.poc.configclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="some")
public class ConfigClientConfiguration {
    private
    String property;

        public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
