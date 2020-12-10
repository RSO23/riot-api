package rso.riotapi.dto.orianna;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SummonerDto
{
    @JsonProperty("id")
    private String id;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("puuid")
    private String puuid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("profileIconId")
    private Integer profileIconId;
    @JsonProperty("summonerLevel")
    private Integer summonerLevel;

}


