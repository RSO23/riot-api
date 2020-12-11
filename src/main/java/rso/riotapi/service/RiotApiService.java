package rso.riotapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import lombok.RequiredArgsConstructor;
import rso.riotapi.config.ConfigRiotApi;
import rso.riotapi.dto.orianna.MatchDto;
import rso.riotapi.dto.orianna.ParticipantDto;
import rso.riotapi.dto.orianna.SummonerDto;
import rso.riotapi.dto.requests.MatchesRegionDto;
import rso.riotapi.dto.requests.UsernameRegionDto;
import rso.riotapi.dto.riotApi.MatchlistDto;

@Service
@RequiredArgsConstructor
public class RiotApiService
{
    private static final Logger log = LoggerFactory.getLogger(RiotApiService.class);
    private static final String API_BASE_URL = "https://euw1.api.riotgames.com/lol/";
    private static final String MATCH_LISTS_BY_ENCRYPTED_ACCOUNT_ID_URL = "match/v4/matchlists/by-account/%s";

    private final ConfigRiotApi configRiotApi;

    private final RestTemplate restTemplate;


    public SummonerDto getSummonerByName(UsernameRegionDto usernameRegionDto)
    {

        Region region = Optional.ofNullable(usernameRegionDto.getRegion()).orElse(Region.EUROPE_WEST);
        Orianna.setDefaultRegion(region);

        Summoner summoner = Orianna.summonerNamed(usernameRegionDto.getUsername()).get();

        SummonerDto summonerDto = new SummonerDto();
        summonerDto.setId(summoner.getId());
        summonerDto.setAccountId(summoner.getAccountId());
        summonerDto.setName(summoner.getName());
        summonerDto.setPuuid(summoner.getPuuid());
        summonerDto.setProfileIconId(summoner.getProfileIcon().getId());
        summonerDto.setSummonerLevel(summoner.getLevel());

        return summonerDto;
    }

    public MatchlistDto getMatchReferences(String accountId)
    {
        // TODO @jakobm Dodaj start/end index
        String url = createURL(MATCH_LISTS_BY_ENCRYPTED_ACCOUNT_ID_URL, accountId);
        ResponseEntity<MatchlistDto> response = createRequest(url, MatchlistDto.class);
        MatchlistDto matchlistDto = response.getBody();
        matchlistDto.setMatches(Arrays.copyOfRange(matchlistDto.getMatches(), 0, 20));

        return matchlistDto;
    }

    public List<MatchDto> getMatchByIds(MatchesRegionDto matchesRegionDto)
    {
        Optional.ofNullable(matchesRegionDto.getRegion())
                .ifPresentOrElse(Orianna::setDefaultRegion, () -> Orianna.setDefaultRegion(Region.EUROPE_WEST));

        return Orianna.matchesWithIds(matchesRegionDto.getMatchIds()).get().stream()
                .map(match -> {
                    MatchDto matchDto = new MatchDto();
                    matchDto.setGameId(match.getId());
                    matchDto.setDuration(match.getDuration().getStandardSeconds());
                    ArrayList<ParticipantDto> participants = new ArrayList<>();

                    match.getParticipants().forEach(participant -> {
                        ParticipantDto participantDto = new ParticipantDto();
                        participantDto.setChampionId(participant.getChampion().getId());
                        participantDto.setProfileIcon(participant.getProfileIcon().getId());
                        participantDto.setKills(participant.getStats().getKills());
                        participantDto.setAssists(participant.getStats().getAssists());
                        participantDto.setDeaths(participant.getStats().getDeaths());
                        participantDto.setLargestMultiKill(participant.getStats().getLargestMultiKill());
                        participantDto.setWin(participant.getStats().isWinner());
                        participantDto.setTeamId(participant.getTeam().getSide().getId());

                        Summoner summoner = participant.getSummoner();
                        participantDto.setUsername(summoner.getName());
                        participantDto.setAccountId(summoner.getAccountId());

                        participants.add(participantDto);
                    });

                    matchDto.setParticipants(participants);
                    return matchDto;
                }).collect(Collectors.toList());
    }

    private String createURL(String url, String parameter)
    {
        String temp = String.format(url, parameter);
        return String.format("%s%s", API_BASE_URL, temp);
    }

    private <T> ResponseEntity<T> createRequest(String url, Class<T> clazz) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Riot-Token", configRiotApi.getRiotApiKey());

        HttpEntity<String> httpEntity = new HttpEntity<>("body", httpHeaders);
        log.info("Making GET request to: " + url);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, clazz);
    }


}
