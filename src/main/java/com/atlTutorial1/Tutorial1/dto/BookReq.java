package com.atlTutorial1.Tutorial1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReq {
    private String name;
    private String isbn;
    private String language;
    private LocalDate publishedAt;
}
