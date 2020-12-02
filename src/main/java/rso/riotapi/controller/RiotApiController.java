package rso.riotapi.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rso.riotapi.dto.ChampionRatesDto;
import rso.riotapi.service.ChampionsApiService;

@RestController
public class RiotApiController
{
    private final ChampionsApiService championsApiService;

    @Autowired
    public RiotApiController(ChampionsApiService championsApiService)
    {
        this.championsApiService = championsApiService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "Pong!";
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
