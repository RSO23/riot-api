package rso.riotapi.dto.riotApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class MatchReferenceDto
{
    @JsonProperty("platformId")
    private String platformId;
    @JsonProperty("gameId")
    private Long gameId;
    @JsonProperty("champion")
    private Integer champion;
    @JsonProperty("queue")
    private Integer queue;
    @JsonProperty("season")
    private Integer season;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("role")
    private String role;
    @JsonProperty("lane")
    private String lane;
}
