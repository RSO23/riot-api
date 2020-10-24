package rso.riotapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rso.riotapi.dto.MatchReferenceDto;
import rso.riotapi.dto.MatchlistDto;
import rso.riotapi.dto.SummonerDto;

@Service
public class RiotApiService
{
    private static final Logger log = LoggerFactory.getLogger(RiotApiService.class);
    private static final String API_TOKEN = "RGAPI-71d4caf7-d817-4d55-94be-fabb465f5749";
    private static final String API_BASE_URL = "https://euw1.api.riotgames.com/lol/";
    private static final String SUMMONER_BY_NAME_URL = "summoner/v4/summoners/by-name/%s";
    private static final String MATCH_BY_ID_URL = "match/v4/matches/%s";
    private static final String MATCH_LISTS_BY_ENCRYPTED_ACCOUNT_ID_URL = "match/v4/matchlists/by-account/%s";

    private final RestTemplate restTemplate;

    public RiotApiService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public SummonerDto getSummonerByName(String username)
    {
        String url = createURL(SUMMONER_BY_NAME_URL, username);
        ResponseEntity<SummonerDto> response = createRequest(url, SummonerDto.class);
        return response.getBody();
    }

    public MatchReferenceDto[] getMatchReferences(String accountId)
    {
        String url = createURL(MATCH_LISTS_BY_ENCRYPTED_ACCOUNT_ID_URL, accountId);
        ResponseEntity<MatchlistDto> response = createRequest(url, MatchlistDto.class);
        return response.getBody().getMatches();
    }

    private String createURL(String url, String parameter)
    {
        String temp = String.format(url, parameter);
        return String.format("%s%s", API_BASE_URL, temp);
    }

    private <T> ResponseEntity<T> createRequest(String url, Class<T> clazz) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Riot-Token", API_TOKEN);

        HttpEntity<String> httpEntity = new HttpEntity<>("body", httpHeaders);
        log.info("Creating request for: " + url);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, clazz);
    }


}
