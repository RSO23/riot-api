package rso.riotapi.dto.requests;

import java.util.List;

import com.merakianalytics.orianna.types.common.Region;

import lombok.Data;

@Data
public class MatchesRegionDto
{
    private List<Long> matchIds;
    private Region region;
}
