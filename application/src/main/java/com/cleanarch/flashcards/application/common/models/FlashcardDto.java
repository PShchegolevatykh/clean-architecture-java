package com.cleanarch.flashcards.application.common.models;

import java.time.Instant;
import java.util.UUID;

public class FlashcardDto {
    private UUID id;
    private String front;
    private String back;
    private Instant createdDate;
    private Instant lastModifiedDate;

    public FlashcardDto() {}

    public FlashcardDto(UUID id, String front, String back, Instant createdDate, Instant lastModifiedDate) {
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
