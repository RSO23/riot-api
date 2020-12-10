package rso.riotapi.dto.orianna;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MatchDto
{
    public long gameId;
    public long gameDuration;
    public Map<Integer, List<ParticipantDto>> teams;
}
