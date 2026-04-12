package com.cleanarch.flashcards.domain.entities;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.assertj.core.api.Assertions.*;

public class FlashcardTest {

    @Test
    void shouldCreateFlashcardWithAllRequiredFields() {
        // Arrange & Act
        Flashcard flashcard = new Flashcard("Front content", "Back content");

        // Assert
        assertThat(flashcard.getId()).isNotNull();
        assertThat(flashcard.getFront()).isEqualTo("Front content");
        assertThat(flashcard.getBack()).isEqualTo("Back content");
        assertThat(flashcard.getCreatedDate()).isNotNull();
        assertThat(flashcard.getLastModifiedDate()).isNotNull();
        assertThat(flashcard.getCreatedDate()).isBeforeOrEqualTo(Instant.now());
    }

    @Test
    void shouldThrowExceptionWhenFrontIsBlank() {
        // Arrange & Act & Assert
        assertThatThrownBy(() -> new Flashcard("", "Back content"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Front cannot be blank");
    }

    @Test
    void shouldThrowExceptionWhenBackIsBlank() {
        // Arrange & Act & Assert
        assertThatThrownBy(() -> new Flashcard("Front content", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Back cannot be blank");
    }

    @Test
    void shouldUpdateFlashcardContent() {
        // Arrange
        Flashcard flashcard = new Flashcard("Original front", "Original back");
        Instant initialModifiedDate = flashcard.getLastModifiedDate();

        // Small delay to ensure timestamp change (optional, Instant.now() is fast)
        try { Thread.sleep(1); } catch (InterruptedException e) {}

        // Act
        flashcard.update("Updated front", "Updated back");

        // Assert
        assertThat(flashcard.getFront()).isEqualTo("Updated front");
        assertThat(flashcard.getBack()).isEqualTo("Updated back");
        assertThat(flashcard.getLastModifiedDate()).isAfter(initialModifiedDate);
    }
}
