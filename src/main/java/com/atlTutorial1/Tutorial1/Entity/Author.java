package com.atlTutorial1.Tutorial1.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)

    private String name;

    @Column(length = 45,nullable = false)
    private String surname;

    @Column(unique = true)
    @Email
    private String email;

    private int age;
    private LocalDate birthdate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    @Column(unique = true)
    private String profilePhotoURL;

    private Role role;


}
