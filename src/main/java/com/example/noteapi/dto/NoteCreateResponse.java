package com.example.noteapi.dto;

import java.util.UUID;

public class NoteCreateResponse {
    public NoteCreateResponse(UUID noteId, String title, String content) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
    }

    public UUID getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    private final UUID noteId;
    private final String title;
    private final String content;
}
