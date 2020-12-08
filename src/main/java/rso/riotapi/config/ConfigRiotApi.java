package rso.riotapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "config-riot-api")
public class ConfigRiotApi
{
    private String championRatesUrl = "http://cdn.merakianalytics.com/riot/lol/resources/latest/en-US/championrates.json";
}
