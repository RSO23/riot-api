package rso.riotapi.dto.requests;

import com.merakianalytics.orianna.types.common.Region;

import lombok.Data;

@Data
public class UsernameRegionDto
{
    private String username;
    private Region region;
}
