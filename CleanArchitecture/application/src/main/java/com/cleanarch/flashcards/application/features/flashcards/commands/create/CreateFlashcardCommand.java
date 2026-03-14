package com.cleanarch.flashcards.application.features.flashcards.commands.create;

import jakarta.validation.constraints.NotBlank;

public class CreateFlashcardCommand {
    @NotBlank(message = "Front cannot be blank")
    private String front;
    
    @NotBlank(message = "Back cannot be blank")
    private String back;

    public CreateFlashcardCommand() {}

    public CreateFlashcardCommand(String front, String back) {
        this.front = front;
        this.back = back;
    }

    public String getFront() { return front; }
    public void setFront(String front) { this.front = front; }

    public String getBack() { return back; }
    public void setBack(String back) { this.back = back; }
}
