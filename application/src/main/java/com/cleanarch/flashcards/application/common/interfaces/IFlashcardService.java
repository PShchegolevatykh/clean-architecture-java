package com.cleanarch.flashcards.application.common.interfaces;

import com.cleanarch.flashcards.application.common.models.FlashcardDto;
import com.cleanarch.flashcards.application.features.flashcards.commands.create.CreateFlashcardCommand;

import java.util.List;
import java.util.UUID;

public interface IFlashcardService {
    FlashcardDto create(CreateFlashcardCommand command);
    FlashcardDto getById(UUID id);
    List<FlashcardDto> getAll();
    void delete(UUID id);
}
