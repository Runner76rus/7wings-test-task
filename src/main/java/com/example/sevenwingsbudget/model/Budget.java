package com.example.sevenwingsbudget.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Min(1900)
    private int year;

    @Min(1)
    @Max(12)
    private int month;

    @Min(1)
    private int amount;

    @Enumerated(EnumType.STRING)
    private BudgetType type;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Budget(int year, int month, int amount, BudgetType type) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.type = type;
    }
}
