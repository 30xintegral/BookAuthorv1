package com.atlTutorial1.Tutorial1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private int age;
    private LocalDate birthdate;

    private String authorPPUrl;

    private List<BookDto> bookList;

    public AuthorDto(Long id, String name, String surname, String email, int age, LocalDate birthdate, List<BookDto> bookList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.birthdate = birthdate;
        this.bookList=bookList;
    }
    public AuthorDto(Long id, String name, String surname, String email, int age, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.birthdate = birthdate;
    }

}
