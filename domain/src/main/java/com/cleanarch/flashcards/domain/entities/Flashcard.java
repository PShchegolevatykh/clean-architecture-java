package com.cleanarch.flashcards.domain.entities;

import com.cleanarch.flashcards.domain.common.BaseEntity;

public class Flashcard extends BaseEntity {
	private String front;
	private String back;

	public Flashcard() {}

	public Flashcard(String front, String back) {
		this.front = front;
		this.back = back;
	}

	public String getFront() { return front; }
	public void setFront(String front) { this.front = front; }

	public String getBack() { return back; }
	public void setBack(String back) { this.back = back; }
}
