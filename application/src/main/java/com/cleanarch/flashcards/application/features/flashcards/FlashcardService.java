package com.cleanarch.flashcards.application.features.flashcards;

import com.cleanarch.flashcards.application.common.interfaces.IFlashcardRepository;
import com.cleanarch.flashcards.application.common.interfaces.IFlashcardService;
import com.cleanarch.flashcards.application.common.models.FlashcardDto;
import com.cleanarch.flashcards.application.features.flashcards.commands.create.CreateFlashcardCommand;
import com.cleanarch.flashcards.domain.common.UuidV7Generator;
import com.cleanarch.flashcards.domain.entities.Flashcard;
import com.cleanarch.flashcards.domain.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlashcardService implements IFlashcardService {

    private static final Logger log = LoggerFactory.getLogger(FlashcardService.class);
    private final IFlashcardRepository repository;

    public FlashcardService(IFlashcardRepository repository) {
        this.repository = repository;
    }

    @Override
    public FlashcardDto create(CreateFlashcardCommand command) {
        log.info("Creating new flashcard with front: {}", command.getFront());
        Flashcard flashcard = new Flashcard(command.getFront(), command.getBack());
        
        flashcard.setId(UuidV7Generator.generate());
        flashcard.setCreatedDate(LocalDateTime.now());
        flashcard.setLastModifiedDate(LocalDateTime.now());
        
        Flashcard saved = repository.save(flashcard);
        log.info("Flashcard created with id: {}", saved.getId());
        return mapToDto(saved);
    }

    @Override
    public FlashcardDto getById(UUID id) {
        log.debug("Fetching flashcard with id: {}", id);
        return repository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> {
                    log.warn("Flashcard not found with id: {}", id);
                    return new NotFoundException("Flashcard not found with id: " + id);
                });
    }

    @Override
    public List<FlashcardDto> getAll() {
        log.debug("Fetching all flashcards");
        return repository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting flashcard with id: {}", id);
        if (repository.findById(id).isEmpty()) {
            log.warn("Attempted to delete non-existent flashcard with id: {}", id);
            throw new NotFoundException("Flashcard not found with id: " + id);
        }
        repository.deleteById(id);
        log.info("Flashcard with id: {} successfully deleted", id);
    }

    private FlashcardDto mapToDto(Flashcard flashcard) {
        return new FlashcardDto(
                flashcard.getId(),
                flashcard.getFront(),
                flashcard.getBack(),
                flashcard.getCreatedDate(),
                flashcard.getLastModifiedDate()
        );
    }
}
