package com.cleanarch.flashcards.api;

import com.cleanarch.flashcards.api.models.TokenRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetTokenAndAccessProtectedResource() throws Exception {
        // 1. Try to access protected resource without token - should fail
        mockMvc.perform(get("/api/v1/flashcards"))
                .andExpect(status().isUnauthorized());

        // 2. Request token
        TokenRequest tokenRequest = new TokenRequest("client-id", "client-secret");
        String tokenResponse = mockMvc.perform(post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tokenRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").exists())
                .andReturn().getResponse().getContentAsString();

        Map<String, Object> responseMap = objectMapper.readValue(tokenResponse, Map.class);
        String token = (String) responseMap.get("access_token");

        // 3. Access protected resource with token - should succeed (even if empty list)
        mockMvc.perform(get("/api/v1/flashcards")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailWithInvalidCredentials() throws Exception {
        TokenRequest tokenRequest = new TokenRequest("wrong-id", "wrong-secret");
        mockMvc.perform(post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tokenRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("invalid_client"));
    }
}
