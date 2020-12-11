package rso.riotapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "config-riot-api")
public class ConfigRiotApi
{
    private String championRatesUrl = "http://cdn.merakianalytics.com/riot/lol/resources/latest/en-US/championrates.json";
    private String riotApiKey = "RGAPI-ff5d2225-5f29-47cd-8566-4a1f43ed6820";
}
