package com.atlTutorial1.Tutorial1.Controller;

import com.atlTutorial1.Tutorial1.Service.AuthorService;
import com.atlTutorial1.Tutorial1.Service.BookService;
import com.atlTutorial1.Tutorial1.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping("/getBooks")
    public List<BookDto> getAllBooks(){
        log.info("called");
        return bookService.getAllBooks();
    }

}
