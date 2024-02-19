package com.atlTutorial1.Tutorial1.Service;

import com.atlTutorial1.Tutorial1.Entity.Author;
import com.atlTutorial1.Tutorial1.Entity.Book;
import com.atlTutorial1.Tutorial1.Repository.AuthorRepository;
import com.atlTutorial1.Tutorial1.Repository.BookRepository;
import com.atlTutorial1.Tutorial1.dto.AuthorDto;
import com.atlTutorial1.Tutorial1.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public BookDto getBookById(Long id) {
//        Book book = bookRepository.getBookById(id);
//        BookDto bookDto = new BookDto(book.getId(), book.getName(), book.getIsbn(), book.getLanguage(), book.getPublishedAt(), book.getAuthor());
        return null;
    }

    @Override
    public List<BookDto> findBooksByAuthorId(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author Not Found") );
        List<BookDto> bookList = new ArrayList<>();
        for (Book book : author.getBooks()) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setName(book.getName());
            bookDto.setLanguage(book.getLanguage());
            bookDto.setPublishedAt(book.getPublishedAt());

            AuthorDto authorDto = new AuthorDto(author.getId(), author.getName(),author.getSurname()
                    ,author.getEmail(), author.getAge(), author.getBirthdate());

            bookDto.setAuthorDto(authorDto);
            bookList.add(bookDto);
        }

        return bookList;
    }


}
