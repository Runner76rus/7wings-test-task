package com.example.sevenwingsbudget.controller;

import com.example.sevenwingsbudget.dto.response.AuthorResponse;
import com.example.sevenwingsbudget.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/author")
public class AuthorController {

    public final AuthorService authorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public AuthorResponse save(@Length(min = 8,max = 128) String fullName){
        return authorService.save(fullName);
    }
}
