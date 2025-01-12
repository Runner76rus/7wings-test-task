package com.example.sevenwingsbudget.dto.request;

import com.example.sevenwingsbudget.model.BudgetType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class BudgetRequest {

    @Min(1900)
    private int year;

    @Min(1)
    @Max(12)
    private int month;

    @Min(1)
    private int amount;

    private BudgetType type;

    @Positive
    private long authorId;
}
