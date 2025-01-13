package com.example.sevenwingsbudget.mapper;

import com.example.sevenwingsbudget.dto.response.AuthorResponse;
import com.example.sevenwingsbudget.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

//    @Mapping(target = "createdAt", expression = "java(author.getCreatedAt().toString())")
    AuthorResponse toAuthorResponse(Author author);
}
