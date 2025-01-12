package com.example.sevenwingsbudget.service;

import com.example.sevenwingsbudget.dto.response.AuthorResponse;
import com.example.sevenwingsbudget.mapper.AuthorMapper;
import com.example.sevenwingsbudget.model.Author;
import com.example.sevenwingsbudget.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponse save(String fullName) {
        Author author = new Author(fullName);
        return authorMapper.toAuthorResponse(authorRepository.save(author));
    }
}
