package rso.riotapi.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rso.riotapi.config.ConfigProperties;
import rso.riotapi.dto.ChampionRatesDto;
import rso.riotapi.service.ChampionsApiService;

@RestController
@RequiredArgsConstructor
public class RiotApiController
{
    private final ConfigProperties configProperties;

    private final ChampionsApiService championsApiService;

    @GetMapping("/ping")
    public String ping() {
        return "Pong!";
    }

    @GetMapping("/config")
    public String testConfig() {
        return configProperties.getTestConfig();
    }

    @GetMapping("/championRates")
    public Map<String, LinkedHashMap> getChampionRates() {
        return championsApiService.getChampionRates();
    }

    @GetMapping("/championRates2")
    public List<ChampionRatesDto> getChampionRates2() {
        return championsApiService.getChampionRates2();
    }
}
