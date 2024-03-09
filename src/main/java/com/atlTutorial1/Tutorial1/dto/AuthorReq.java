package com.atlTutorial1.Tutorial1.dto;

import com.atlTutorial1.Tutorial1.Entity.Book;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorReq {
    private String name;

    private String surname;

    @Email(message = "Incorrect Email")
    private String email;

    private int age;
    private LocalDate birthdate;

    private List<BookReq> bookReqs = new ArrayList<>();

}
