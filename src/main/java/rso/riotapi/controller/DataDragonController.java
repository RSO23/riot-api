package rso.riotapi.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rso.riotapi.dto.championRates.ChampionRatesDto;
import rso.riotapi.service.ChampionsApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Basic operations", produces = "application/json", consumes = "application/json")
public class DataDragonController
{
    private final Logger log = LoggerFactory.getLogger(DataDragonController.class);

    private final ChampionsApiService championsApiService;

    @GetMapping("/championRates")
    public Map<String, LinkedHashMap> getChampionRates() {
        return championsApiService.getChampionRates();
    }

    @GetMapping("/championRates2")
    public List<ChampionRatesDto> getChampionRates2() {
        return championsApiService.getChampionRates2();
    }
}
