package com.example.noteapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteCreateDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Title is required")
    private String content;
}