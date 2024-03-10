package com.atlTutorial1.Tutorial1.Service;

import com.atlTutorial1.Tutorial1.Entity.Author;
import com.atlTutorial1.Tutorial1.Entity.Book;
import com.atlTutorial1.Tutorial1.Repository.AuthorRepository;
import com.atlTutorial1.Tutorial1.Repository.BookRepository;
import com.atlTutorial1.Tutorial1.dto.AuthorDto;
import com.atlTutorial1.Tutorial1.dto.AuthorReq;
import com.atlTutorial1.Tutorial1.dto.BookDto;
import com.atlTutorial1.Tutorial1.dto.BookReq;
import com.atlTutorial1.Tutorial1.exception.NotFoundException;
import com.atlTutorial1.Tutorial1.mapper.AuthorMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService{


    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;


    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findAuthorById(id).orElseThrow(() -> new NotFoundException("No such author found by given id"));
        List<Book> bookList = bookRepository.findBooksByAuthorId(id);
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book: bookList
             ) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setName(book.getName());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setLanguage(book.getLanguage());
            bookDto.setPublishedAt(book.getPublishedAt());
            bookDtoList.add(bookDto);
        }
        AuthorDto authorDto =
                AuthorMapper.INSTANCE.mapEntityToDto(author); //book map etmedi deyesen
        authorDto.setAuthorPPUrl("static/" + author.getProfilePhotoURL());
        authorDto.setBookList(bookDtoList);

        return authorDto;
    }

    @Override
    public AuthorDto getAuthorByBookId(Long bookId) {
        Book book = bookRepository.findBookById(bookId).orElseThrow(()-> new NotFoundException("Book not found by given id"));

        Author author = authorRepository.findAuthorByBookId(bookId).orElseThrow(()-> new RuntimeException("Nu such author found by given book id"));
        if (author==null){
            throw new RuntimeException("No such author wrote that book");
        }
        List<BookDto> bookDtoList = new ArrayList<>();
        BookDto bookDto = new BookDto(book.getId(), book.getName(), book.getIsbn(), book.getLanguage(), book.getPublishedAt());
        bookDtoList.add(bookDto);
        AuthorDto authorDto = new AuthorDto(author.getId(), author.getName(), author.getSurname(), author.getEmail(), author.getAge(), author.getBirthdate(), author.getProfilePhotoURL() ,bookDtoList);
        return authorDto;
    }

    @Override
    public String saveAuthor(AuthorReq authorReq, MultipartFile authorPP) {
        Path root = Paths.get("Tutorial1/src/main/resources/static");
        String fileName = authorPP.getOriginalFilename();
        try{
            Author author = new Author();
            author.setName(authorReq.getName());
            author.setSurname(authorReq.getSurname());
            author.setEmail(authorReq.getEmail());
            author.setAge(authorReq.getAge());
            author.setBirthdate(authorReq.getBirthdate());
            author.setProfilePhotoURL(authorPP.getOriginalFilename());

            for (BookReq bookReq: authorReq.getBookReqs()) {
                Book book1 = new Book();
                book1.setName(bookReq.getBookName());
                book1.setIsbn(bookReq.getIsbn());
                book1.setLanguage(bookReq.getLanguage());
                book1.setPublishedAt(bookReq.getPublishedAt());

                book1.setAuthor(author);
            }

            Files.copy(authorPP.getInputStream(), Paths.get(new ClassPathResource("static").getURI()).resolve(authorPP.getOriginalFilename()));


            authorRepository.save(author);
            return "Uploaded";
        } catch (IOException e) {
            throw new RuntimeException(e + "IOException");
        }catch (RuntimeException e){
            throw new RuntimeException("Runtime Exception");
        }

    }

    @Override
    public String updateAuthorById(Long authorId, AuthorReq authorReq, MultipartFile authorPP) {
        Author author = authorRepository.findAuthorById(authorId).orElseThrow(()-> new NotFoundException("no such author found by given id"));

        String fileName = authorPP.getOriginalFilename();
//        if (author==null){
//            throw new RuntimeException("Author not found");
//        }
        try {
            author.setName(authorReq.getName());
            author.setSurname(authorReq.getSurname());
            author.setAge(authorReq.getAge());
            author.setEmail(authorReq.getEmail());
            author.setBirthdate(authorReq.getBirthdate());
            Files.copy(authorPP.getInputStream(), Paths.get(new ClassPathResource("static").getURI()).resolve(authorPP.getOriginalFilename()));
            author.setProfilePhotoURL(authorPP.getOriginalFilename());
        }catch (java.io.IOException e){
            throw new RuntimeException("File error");
        }

        authorRepository.save(author);
        return "Updating done";

    }

    @Override
    public AuthorDto getAuthorByEmail(String email) {
        Author author = authorRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("No such author found"));
        List<Book> bookList = bookRepository.findBooksByAuthorId(author.getId());
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book: bookList
        ) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setName(book.getName());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setLanguage(book.getLanguage());
            bookDto.setPublishedAt(book.getPublishedAt());
            bookDtoList.add(bookDto);
        }
        AuthorDto authorDto =
                AuthorMapper.INSTANCE.mapEntityToDto(author); //book map etmedi deyesen
        authorDto.setAuthorPPUrl("static/" + author.getProfilePhotoURL());
        authorDto.setBookList(bookDtoList);

        return authorDto;
    }
}
