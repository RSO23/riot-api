package rso.riotapi.dto.riotApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import rso.riotapi.dto.riotApi.MatchReferenceDto;

@Data
public class MatchlistDto
{
    @JsonProperty("startIndex")
    private Integer startIndex;
    @JsonProperty("totalGames")
    private Integer totalGames;
    @JsonProperty("endIndex")
    private Integer endIndex;
    @JsonProperty("matches")
    private MatchReferenceDto[] matches;
}
