package rso.riotapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChampionRatesRoleDto
{
    @JsonProperty("playRate")
    private Double playRate;
    @JsonProperty("winRate")
    private Double winRate;
    @JsonProperty("banRate")
    private Double banRate;
}
