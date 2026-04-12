package com.cleanarch.flashcards.domain.entities;

import com.cleanarch.flashcards.domain.common.BaseEntity;
import com.cleanarch.flashcards.domain.common.UuidV7Generator;
import java.time.Instant;

public class Flashcard extends BaseEntity {
	private String front;
	private String back;

	public Flashcard(String front, String back) {
		validate(front, back);
		this.front = front;
		this.back = back;
		setId(UuidV7Generator.generate());
		setCreatedDate(Instant.now());
		setLastModifiedDate(Instant.now());
	}

	public void update(String front, String back) {
		validate(front, back);
		this.front = front;
		this.back = back;
		setLastModifiedDate(Instant.now());
	}

	private void validate(String front, String back) {
		if (front == null || front.isBlank()) {
			throw new IllegalArgumentException("Front cannot be blank");
		}
		if (back == null || back.isBlank()) {
			throw new IllegalArgumentException("Back cannot be blank");
		}
	}

	public String getFront() { return front; }
	public String getBack() { return back; }
}
