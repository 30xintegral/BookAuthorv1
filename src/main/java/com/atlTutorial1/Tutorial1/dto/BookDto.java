package com.atlTutorial1.Tutorial1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;

    private String name;
    private String isbn;
    private String language;
    private LocalDate publishedAt;
    private AuthorDto authorDto;

    public BookDto(Long id, String name, String isbn, String language, LocalDate publishedAt) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.language = language;
        this.publishedAt = publishedAt;
    }
}
