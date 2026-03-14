package com.cleanarch.flashcards.infrastructure.persistence.repositories;

import com.cleanarch.flashcards.infrastructure.persistence.entities.FlashcardJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlashcardJpaRepository extends JpaRepository<FlashcardJpaEntity, UUID> {
}
