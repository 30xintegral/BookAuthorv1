package com.atlTutorial1.Tutorial1.Service;

import com.atlTutorial1.Tutorial1.Entity.Author;
import com.atlTutorial1.Tutorial1.Entity.Book;
import com.atlTutorial1.Tutorial1.dto.AuthorDto;
import com.atlTutorial1.Tutorial1.dto.AuthorReq;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    AuthorDto getAuthorByBookId(Long bookId);

    String saveAuthor(AuthorReq authorReq, MultipartFile authorPP);

    String updateAuthorById(Long authorId, AuthorReq authorReq, MultipartFile authorPP);

    AuthorDto getAuthorByEmail(String email);

}
