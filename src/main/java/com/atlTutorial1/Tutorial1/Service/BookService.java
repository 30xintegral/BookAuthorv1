package com.atlTutorial1.Tutorial1.Service;

import com.atlTutorial1.Tutorial1.Entity.Author;
import com.atlTutorial1.Tutorial1.Entity.Book;
import com.atlTutorial1.Tutorial1.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto getBookById(Long id);

    List<BookDto> findBooksByAuthorId(Long authorId);

//    BookDto getBookByName(String name);
}
