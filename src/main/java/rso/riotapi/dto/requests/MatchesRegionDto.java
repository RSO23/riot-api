package rso.riotapi.dto.requests;

import java.util.Set;

import com.merakianalytics.orianna.types.common.Region;

import lombok.Data;

@Data
public class MatchesRegionDto
{
    private Set<Long> matchIds;
    private Region region;
}
