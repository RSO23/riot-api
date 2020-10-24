package rso.riotapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
