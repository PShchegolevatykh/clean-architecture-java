package com.cleanarch.flashcards.api.controllers;

import com.cleanarch.flashcards.application.common.models.FlashcardDto;
import com.cleanarch.flashcards.application.features.flashcards.commands.create.CreateFlashcardCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FlashcardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateAndGetFlashcard() throws Exception {
        CreateFlashcardCommand command = new CreateFlashcardCommand("What is Clean Architecture?", "A software design philosophy that separates concerns.");

        String content = mockMvc.perform(post("/api/v1/flashcards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.front").value(command.getFront()))
                .andExpect(jsonPath("$.back").value(command.getBack()))
                .andReturn().getResponse().getContentAsString();

        FlashcardDto created = objectMapper.readValue(content, FlashcardDto.class);

        mockMvc.perform(get("/api/v1/flashcards/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.front").value(command.getFront()))
                .andExpect(jsonPath("$.back").value(command.getBack()));
    }
}
