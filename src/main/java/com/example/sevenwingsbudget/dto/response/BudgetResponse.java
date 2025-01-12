package com.example.sevenwingsbudget.dto.response;

import com.example.sevenwingsbudget.model.BudgetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetResponse{

    private Long id;
    private int year;
    private int month;
    private int amount;
    private BudgetType type;
    private String fullName;
    private String createdAt;
}
