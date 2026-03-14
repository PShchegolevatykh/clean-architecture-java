package com.cleanarch.flashcards.infrastructure.persistence.repositories;

import com.cleanarch.flashcards.application.common.interfaces.IFlashcardRepository;
import com.cleanarch.flashcards.domain.entities.Flashcard;
import com.cleanarch.flashcards.infrastructure.persistence.entities.FlashcardJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FlashcardRepositoryImpl implements IFlashcardRepository {

    private final FlashcardJpaRepository jpaRepository;

    public FlashcardRepositoryImpl(FlashcardJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Flashcard save(Flashcard flashcard) {
        FlashcardJpaEntity entity = mapToJpaEntity(flashcard);
        FlashcardJpaEntity saved = jpaRepository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Flashcard> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public List<Flashcard> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private FlashcardJpaEntity mapToJpaEntity(Flashcard flashcard) {
        return new FlashcardJpaEntity(
                flashcard.getId(),
                flashcard.getFront(),
                flashcard.getBack(),
                flashcard.getCreatedDate(),
                flashcard.getLastModifiedDate()
        );
    }

    private Flashcard mapToDomain(FlashcardJpaEntity entity) {
        Flashcard flashcard = new Flashcard(entity.getFront(), entity.getBack());
        flashcard.setId(entity.getId());
        flashcard.setCreatedDate(entity.getCreatedDate());
        flashcard.setLastModifiedDate(entity.getLastModifiedDate());
        return flashcard;
    }
}
