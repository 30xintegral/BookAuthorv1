package com.atlTutorial1.Tutorial1.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String isbn;
    private String language;
    private LocalDate publishedAt;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public void setAuthor(Author author) {
        this.author = author;
        author.getBooks().add(this);
    }
}
