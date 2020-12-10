package rso.riotapi.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import rso.riotapi.config.ConfigRiotApi;
import rso.riotapi.dto.championRates.ChampionRatesDto;
import rso.riotapi.dto.championRates.ChampionRatesRoleDto;
import rso.riotapi.enums.RoleEnum;

@Service
@RequiredArgsConstructor
public class ChampionsApiService
{
    private static final Logger log = LoggerFactory.getLogger(ChampionsApiService.class);

    private final ConfigRiotApi configRiotApi;

    private final RestTemplate restTemplate;

    public List<ChampionRatesDto> getChampionRates2()
    {
        log.info("getChampionRates2 called");
        return ((LinkedHashMap<String, LinkedHashMap<?, ?>>) restTemplate.getForObject(configRiotApi.getChampionRatesUrl(), Map.class).get("data")).entrySet().stream()
                .map(mapEntry -> {
                    int championId = Integer.parseInt(mapEntry.getKey());
                    Map<RoleEnum, ChampionRatesRoleDto> collect = mapEntry.getValue().entrySet().stream()
                            .map(entry -> {
                                RoleEnum roleEnum = Enum.valueOf(RoleEnum.class, entry.getKey().toString());
                                Object value = entry.getValue();
                                Double playRate = null;
                                Double winRate = null;
                                Double banRate = null;
                                if (value instanceof LinkedHashMap)
                                {
                                    playRate = ((Double) ((LinkedHashMap<?, ?>) value).get("playRate"));
                                    winRate = ((Double) ((LinkedHashMap<?, ?>) value).get("winRate"));
                                    banRate = ((Double) ((LinkedHashMap<?, ?>) value).get("banRate"));
                                }
                                return Map.entry(roleEnum, new ChampionRatesRoleDto(playRate, winRate, banRate));
                            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    return Map.entry(championId, collect);
                })
                .map(integerMapEntry -> new ChampionRatesDto(integerMapEntry.getKey(), integerMapEntry.getValue()))
                .collect(Collectors.toList());
    }

    public Map<String, LinkedHashMap> getChampionRates()
    {
        log.info("getChampionRates called");
        return ((LinkedHashMap<String, LinkedHashMap>) restTemplate.getForObject(configRiotApi.getChampionRatesUrl(), LinkedHashMap.class).get("data"));
    }

}
