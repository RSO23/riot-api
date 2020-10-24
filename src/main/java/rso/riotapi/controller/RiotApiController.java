package rso.riotapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rso.riotapi.dto.MatchReferenceDto;
import rso.riotapi.dto.SummonerDto;
import rso.riotapi.service.RiotApiService;

@RestController
public class RiotApiController
{
    private RiotApiService riotApiService;

    @Autowired
    public RiotApiController(RiotApiService riotApiService)
    {
        this.riotApiService = riotApiService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "Pong!";
    }

    @GetMapping("/summoner/{username}")
    public SummonerDto getSummoner(@PathVariable("username") String username) {
        return riotApiService.getSummonerByName(username);
    }

    @GetMapping("/matches/{accountId}")
    public MatchReferenceDto[] getMatchReferences(@PathVariable("accountId") String accountId) {
        return riotApiService.getMatchReferences(accountId);
    }
}
