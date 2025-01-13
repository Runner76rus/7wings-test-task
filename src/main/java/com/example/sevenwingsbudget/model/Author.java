package com.example.sevenwingsbudget.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 8, max = 128)
    private String fullName;

    private LocalDateTime createdAt;

    public Author(String fullName) {
        this.fullName = fullName;
        this.createdAt = LocalDateTime.now().withNano(0);
    }

    public Author() {
        createdAt = LocalDateTime.now().withNano(0);
    }
}


