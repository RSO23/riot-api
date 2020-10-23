package rso.riotapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiotApiController
{
    @GetMapping("/ping")
    public String ping() {
        return "Pong!";
    }
}
