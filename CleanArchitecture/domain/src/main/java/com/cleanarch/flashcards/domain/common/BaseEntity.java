package com.cleanarch.flashcards.domain.common;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEntity {
    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public LocalDateTime getLastModifiedDate() { return lastModifiedDate; }
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }
}
