package rso.riotapi.dto.orianna;

import lombok.Data;

@Data
public class ParticipantDto
{
    public String username;
    public String accountId;
    public int profileIcon;
    public int championId;
    public boolean win;
    public int kills;
    public int deaths;
    public int assists;
    public int largestMultiKill;
}
