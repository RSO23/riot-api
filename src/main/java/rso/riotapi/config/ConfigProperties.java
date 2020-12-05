package rso.riotapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "config")
public class ConfigProperties
{
    private String testConfig = "Change this text with Consul";
}