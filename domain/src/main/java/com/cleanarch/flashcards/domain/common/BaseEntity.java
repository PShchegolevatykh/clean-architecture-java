package com.cleanarch.flashcards.domain.common;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseEntity {
    private UUID id;
    private Instant createdDate;
    private Instant lastModifiedDate;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Instant getCreatedDate() { return createdDate; }
    public void setCreatedDate(Instant createdDate) { this.createdDate = createdDate; }

    public Instant getLastModifiedDate() { return lastModifiedDate; }
    public void setLastModifiedDate(Instant lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }
}
