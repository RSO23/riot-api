package rso.riotapi.dto.orianna;

import lombok.Data;

@Data
public class ParticipantDto
{
    private String username;
    private String accountId;
    private int profileIcon;
    private String champion;
    private boolean win;
    private int kills;
    private int deaths;
    private int assists;
    private int largestMultiKill;
    private int teamId;
}
