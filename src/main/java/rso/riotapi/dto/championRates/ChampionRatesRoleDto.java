package rso.riotapi.dto.championRates;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
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
