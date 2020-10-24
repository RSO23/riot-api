package rso.riotapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
    @JsonProperty("revisionDate")
    private Long revisionDate;
    @JsonProperty("summonerLevel")
    private Integer summonerLevel;

}


