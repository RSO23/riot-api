package rso.riotapi.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rso.riotapi.enums.RoleEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChampionRatesDto
{
    private Integer championId;

    private Map<RoleEnum, ChampionRatesRoleDto> championRatesByRole;
}
