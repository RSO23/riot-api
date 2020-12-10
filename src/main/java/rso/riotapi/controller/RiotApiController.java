package rso.riotapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rso.riotapi.config.ConfigProperties;
import rso.riotapi.dto.orianna.MatchDto;
import rso.riotapi.dto.requests.MatchesRegionDto;
import rso.riotapi.dto.riotApi.MatchlistDto;
import rso.riotapi.dto.orianna.SummonerDto;
import rso.riotapi.dto.requests.UsernameRegionDto;
import rso.riotapi.service.RiotApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Basic operations", produces = "application/json", consumes = "application/json")
public class RiotApiController
{
    private final Logger log = LoggerFactory.getLogger(RiotApiController.class);

    private final ConfigProperties configProperties;

    private final RiotApiService riotApiService;

    @GetMapping("/ping")
    public String ping() {
        log.info("Ping!");
        return "Pong!";
    }

    @GetMapping("/config")
    public String testConfig() {
        return configProperties.getTestConfig();
    }

    @GetMapping("/summoner")
    public SummonerDto getSummonerByUsername(UsernameRegionDto usernameRegionDto)
    {
        return riotApiService.getSummonerByName(usernameRegionDto);
    }

    @GetMapping("/matches/references/{accountId}")
    public MatchlistDto getMatchReferencesByAccountId(@PathVariable String accountId)
    {
        return riotApiService.getMatchReferences(accountId);
    }

    @PostMapping("/matches")
    public List<MatchDto> getMatchesByIds(MatchesRegionDto matchesRegionDto) {
        return riotApiService.getMatchByIds(matchesRegionDto);
    }
}
