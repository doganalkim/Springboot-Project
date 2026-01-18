package com.example.noteapi.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NoteCreateResponse {
    public NoteCreateResponse(UUID noteId, String title, String content) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
    }

    private final UUID noteId;
    private final String title;
    private final String content;
}
