package com.eliel.inotes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;
}
