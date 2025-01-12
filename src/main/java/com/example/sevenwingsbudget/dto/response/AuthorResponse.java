package com.example.sevenwingsbudget.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private Long id;
    private String fullName;
    private String createdAt;
}
