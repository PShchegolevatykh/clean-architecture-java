package com.cleanarch.flashcards.application.common.interfaces;

import com.cleanarch.flashcards.domain.entities.Flashcard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlashcardRepository {
    Flashcard save(Flashcard flashcard);
    Optional<Flashcard> findById(UUID id);
    List<Flashcard> findAll();
    void deleteById(UUID id);
}
