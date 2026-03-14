package com.cleanarch.flashcards.api.controllers;

import com.cleanarch.flashcards.application.common.interfaces.IFlashcardService;
import com.cleanarch.flashcards.application.common.models.FlashcardDto;
import com.cleanarch.flashcards.application.features.flashcards.commands.create.CreateFlashcardCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/flashcards")
public class FlashcardsController {

    private final IFlashcardService flashcardService;

    public FlashcardsController(IFlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping
    public ResponseEntity<FlashcardDto> create(@Valid @RequestBody CreateFlashcardCommand command) {
        FlashcardDto result = flashcardService.create(command);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardDto> getById(@PathVariable UUID id) {
        FlashcardDto result = flashcardService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<FlashcardDto>> getAll() {
        List<FlashcardDto> result = flashcardService.getAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        flashcardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
