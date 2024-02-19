package com.atlTutorial1.Tutorial1.Repository;

import com.atlTutorial1.Tutorial1.Entity.Author;
import com.atlTutorial1.Tutorial1.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookById(Long id);

    Book getBookByName(String name);

    List<Book> findByAuthor(Author author);

    List<Book> findBooksByAuthorId(Long authorId);


}
