package rso.riotapi.cotroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import rso.riotapi.controller.RiotApiController;
import rso.riotapi.service.RiotApiService;

@WebMvcTest(RiotApiController.class)
public class RiotApiControllerIT
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RiotApiService riotApiService;

    @Test
    void pingShouldReturnPong() throws Exception {
        mockMvc.perform(get("/ping"))
                .andExpect(status().isOk());
    }

}
