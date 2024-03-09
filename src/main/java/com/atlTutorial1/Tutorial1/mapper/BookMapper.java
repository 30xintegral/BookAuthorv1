package com.atlTutorial1.Tutorial1.mapper;

import com.atlTutorial1.Tutorial1.Entity.Book;
import com.atlTutorial1.Tutorial1.dto.AuthorDto;
import com.atlTutorial1.Tutorial1.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class BookMapper {
    public static final BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    public abstract BookDto mapEntityToDto(Book book);
}
