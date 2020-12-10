package rso.riotapi.dto.championRates;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import rso.riotapi.enums.RoleEnum;

@Data
@AllArgsConstructor
public class ChampionRatesDto
{
    private Integer championId;

    private Map<RoleEnum, ChampionRatesRoleDto> championRatesByRole;
}
