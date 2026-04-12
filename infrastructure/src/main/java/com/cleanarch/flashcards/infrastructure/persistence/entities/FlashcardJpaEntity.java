package com.cleanarch.flashcards.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "flashcards")
public class FlashcardJpaEntity {
    @Id
    private UUID id;

    private String front;
    private String back;

    private Instant createdDate;
    private Instant lastModifiedDate;

    public FlashcardJpaEntity() {}

    public FlashcardJpaEntity(UUID id, String front, String back, Instant createdDate, Instant lastModifiedDate) {
        this.id = id;
        this.front = front;
        this.back = back;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFront() { return front; }
    public void setFront(String front) { this.front = front; }

    public String getBack() { return back; }
    public void setBack(String back) { this.back = back; }

    public Instant getCreatedDate() { return createdDate; }
    public void setCreatedDate(Instant createdDate) { this.createdDate = createdDate; }

    public Instant getLastModifiedDate() { return lastModifiedDate; }
    public void setLastModifiedDate(Instant lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }
}
