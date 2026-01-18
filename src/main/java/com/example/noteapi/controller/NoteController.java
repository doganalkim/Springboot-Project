package com.example.noteapi.controller;

import com.example.noteapi.dto.NoteCreateResponse;
import com.example.noteapi.service.NoteService;
import com.example.noteapi.dto.NoteCreateDTO;
import com.example.noteapi.entity.Note;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteCreateResponse> createNote(@Valid @RequestBody NoteCreateDTO noteDTO) {
        Note CreatedNote = noteService.createNote(noteDTO);
        return  ResponseEntity.ok(
                new NoteCreateResponse(
                        CreatedNote.getNoteId(),
                        CreatedNote.getTitle(),
                        CreatedNote.getContent())
        );
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
}
