package com.example.sevenwingsbudget.service;

import com.example.sevenwingsbudget.dto.response.AuthorResponse;

public interface AuthorService {

    AuthorResponse save(String fullName);
}
