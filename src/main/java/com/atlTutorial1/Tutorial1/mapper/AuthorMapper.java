package com.atlTutorial1.Tutorial1.mapper;

import com.atlTutorial1.Tutorial1.Entity.Author;
import com.atlTutorial1.Tutorial1.dto.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class AuthorMapper {
    public static final AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    public abstract AuthorDto mapEntityToDto(Author author);
}
