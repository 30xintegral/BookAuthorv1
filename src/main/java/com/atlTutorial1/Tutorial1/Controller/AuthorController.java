package com.atlTutorial1.Tutorial1.Controller;

import com.atlTutorial1.Tutorial1.Repository.AuthorRepository;
import com.atlTutorial1.Tutorial1.Service.AuthorService;
import com.atlTutorial1.Tutorial1.Service.BookService;
import com.atlTutorial1.Tutorial1.dto.AuthorDto;
import com.atlTutorial1.Tutorial1.dto.AuthorReq;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/home")
@Slf4j
public class AuthorController {

    AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/author")
    AuthorDto getAuthor(@RequestParam Long id){
        log.info("called");
        return authorService.getAuthorById(id);
    }


    @GetMapping("/authors/{authorId}/books")
    public ResponseEntity<?> getBooksByAuthorId(@PathVariable Long authorId){
        return ResponseEntity.ok(bookService.findBooksByAuthorId(authorId));
    }

    @PostMapping("/register")
    public String saveAuthor(@Valid @ModelAttribute AuthorReq authorReq, @RequestParam("file") MultipartFile authorPP){
        log.info("saveAuthor called ");
        return authorService.saveAuthor(authorReq, authorPP);
    }

    @PutMapping("/author/{id}")
    public String updateAuthor(@PathVariable Long id ,@ModelAttribute AuthorReq authorReq, @RequestParam("file") MultipartFile authorPP){
        log.info("author updating controller called");
        return authorService.updateAuthorById(id, authorReq, authorPP);
    }



}
