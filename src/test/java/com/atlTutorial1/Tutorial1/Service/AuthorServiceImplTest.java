package com.atlTutorial1.Tutorial1.Service;

import com.atlTutorial1.Tutorial1.Entity.Author;
import com.atlTutorial1.Tutorial1.Entity.Book;
import com.atlTutorial1.Tutorial1.Repository.AuthorRepository;
import com.atlTutorial1.Tutorial1.Repository.BookRepository;
import com.atlTutorial1.Tutorial1.dto.AuthorDto;
import com.atlTutorial1.Tutorial1.dto.AuthorReq;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.FileInfo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatRuntimeException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void givenGetAuthorByIdWhenAuthorFoundAndBookFoundThenReturnDto(){
        //arrange
        Long authorId = 1L;
        Book book = Book.builder()
                .id(authorId)
                .isbn("111-1111-1-11")
                .publishedAt(LocalDate.now())
                .language("AZE")
                .build();
        Author author = Author.builder()
                .id(authorId)
                .name("Test")
                .surname("Testov")
                .age(20)
                .email("Test@gmail.com")
                .books(List.of(book))
                .build();
        when(authorRepository.findAuthorById(anyLong())).thenReturn(Optional.of(author));
        when(bookRepository.findBooksByAuthorId(anyLong())).thenReturn(List.of(book));
        //act
        AuthorDto result = authorService.getAuthorById(1L);

        assertNotNull(result);
    }

    @Test
    public void givenGetAuthorByIdWhenAuthorNotFoundThenThrowException(){
        when(authorRepository.findAuthorById(anyLong())).thenReturn(Optional.empty());

        //act & assert

        assertThatThrownBy(
                ()->authorService.getAuthorById(1L)).isInstanceOf(RuntimeException.class).hasMessage("No such author found by given id");
    }
    @Test
    public void givenGetAuthorByIdWhenAuthorFoundAndBookNotFoundThenThrowException(){
        when(authorRepository.findAuthorById(anyLong())).thenReturn(Optional.of(new Author()));
        when(bookRepository.findBooksByAuthorId(anyLong())).thenThrow(new RuntimeException());

        assertThatThrownBy(()-> authorService.getAuthorById(1L)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void givenGetAuthorByBookIdWhenBookFoundAndAuthorFoundThenReturnDto(){
        //arrange
        Long authorId = 1L;
        Book book = Book.builder()
                .id(1L)
                .isbn("111-1111-1-11")
                .publishedAt(LocalDate.now())
                .language("AZE")
                .build();
        Author author = Author.builder()
                .id(authorId)
                .name("Test")
                .surname("Testov")
                .age(20)
                .email("Test@gmail.com")
                .books(List.of(book))
                .build();

        when(bookRepository.findBookById(anyLong())).thenReturn(Optional.of(book));
        when(authorRepository.findAuthorByBookId(anyLong())).thenReturn(Optional.of(author));

        AuthorDto result = authorService.getAuthorByBookId(1L);

        assertNotNull(result);

    }

    @Test
    public void givenGetAuthorByBookIdWhenBookNotThenThrowException(){
        when(bookRepository.findBookById(anyLong())).thenThrow(new RuntimeException());

        assertThatThrownBy(
                ()-> authorService.getAuthorByBookId(1L)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void givenUpdateAuthorByIdWhenAuthorFoundThenReturnNothing() throws IOException {
        AuthorReq authorReq = Mockito.mock(AuthorReq.class);
        Author author = Mockito.mock(Author.class);



        MockMultipartFile file = new MockMultipartFile("file", new byte[0]);

        when(authorRepository.findAuthorById(anyLong())).thenReturn(Optional.of(author));
//        when(Files.copy(file.getInputStream(), Path.of(new ClassPathResource("static").getURI()).resolve(file.getOriginalFilename()))).thenReturn(any());


        String result = authorService.updateAuthorById(1L, authorReq, file);

        verify(authorRepository, times(1)).save(any());
        //error aliriq
    }

    @Test
    public void givenGetAuthorByEmailWhenAuthorFoundAndBookFoundThenReturn(){
        Author author = mock(Author.class);
        Book book = mock(Book.class);
        AuthorDto authorDto;

        when(authorRepository.findByEmail(anyString())).thenReturn(Optional.of(author));
        when(bookRepository.findBooksByAuthorId(anyLong())).thenReturn(List.of(book));

        authorDto = authorService.getAuthorByEmail(anyString());

        assertNotNull(authorDto);
    }
}