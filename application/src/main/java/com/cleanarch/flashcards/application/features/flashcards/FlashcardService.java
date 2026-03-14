package com.cleanarch.flashcards.application.features.flashcards;

import com.cleanarch.flashcards.application.common.interfaces.IFlashcardRepository;
import com.cleanarch.flashcards.application.common.interfaces.IFlashcardService;
import com.cleanarch.flashcards.application.common.models.FlashcardDto;
import com.cleanarch.flashcards.application.features.flashcards.commands.create.CreateFlashcardCommand;
import com.cleanarch.flashcards.domain.common.UuidV7Generator;
import com.cleanarch.flashcards.domain.entities.Flashcard;
import com.cleanarch.flashcards.domain.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlashcardService implements IFlashcardService {

    private final IFlashcardRepository repository;

    public FlashcardService(IFlashcardRepository repository) {
        this.repository = repository;
    }

    @Override
    public FlashcardDto create(CreateFlashcardCommand command) {
        Flashcard flashcard = new Flashcard(command.getFront(), command.getBack());
        
        flashcard.setId(UuidV7Generator.generate());
        flashcard.setCreatedDate(LocalDateTime.now());
        flashcard.setLastModifiedDate(LocalDateTime.now());
        
        Flashcard saved = repository.save(flashcard);
        return mapToDto(saved);
    }

    @Override
    public FlashcardDto getById(UUID id) {
        return repository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new NotFoundException("Flashcard not found with id: " + id));
    }

    @Override
    public List<FlashcardDto> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Flashcard not found with id: " + id);
        }
        repository.deleteById(id);
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
