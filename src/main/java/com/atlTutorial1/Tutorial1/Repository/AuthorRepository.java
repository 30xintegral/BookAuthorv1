package com.atlTutorial1.Tutorial1.Repository;

import com.atlTutorial1.Tutorial1.Entity.Author;
import com.atlTutorial1.Tutorial1.Entity.Book;
import com.atlTutorial1.Tutorial1.dto.AuthorReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author getAuthorById(Long id);

    @Query(value = "select a.* from author a where a.id in (select id from books where id=:id)", nativeQuery = true)
    Author getAuthorByBookId(@Param(value = "id") Long bookId);


//    @Modifying
//    void updateAuthorById(Long id, AuthorReq authorReq, String authorPP);


}
