package com.example.noteapi.service;

import com.example.noteapi.repository.NoteRepository;
import com.example.noteapi.entity.Note;
import com.example.noteapi.dto.NoteCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note createNote(NoteCreateDTO noteDTO) {
        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());

        UUID userId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        note.setUserId(userId);

        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
}