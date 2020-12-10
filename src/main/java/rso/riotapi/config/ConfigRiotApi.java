package rso.riotapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "config-riot-api")
public class ConfigRiotApi
{
    private String championRatesUrl = "http://cdn.merakianalytics.com/riot/lol/resources/latest/en-US/championrates.json";
    private String riotApiKey = "RGAPI-e7c19857-88fc-4a87-a541-7d99995efa0c";
}
