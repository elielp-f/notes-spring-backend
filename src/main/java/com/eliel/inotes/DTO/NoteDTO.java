package com.eliel.inotes.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class NoteDTO {
    private Long id;
    @NotBlank(message = "Title is required.")
    private String title;
    private String content;
}
