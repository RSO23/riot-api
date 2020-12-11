package rso.riotapi.dto.orianna;

import java.util.List;

import lombok.Data;

@Data
public class MatchDto
{
    public long gameId;
    public long duration;
    public List<ParticipantDto> participants;
}
