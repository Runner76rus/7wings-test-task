package com.example.sevenwingsbudget.service;

import com.example.sevenwingsbudget.dto.request.BudgetRequest;
import com.example.sevenwingsbudget.dto.response.BudgetResponse;
import com.example.sevenwingsbudget.dto.response.BudgetYearStatsResponse;
import org.springframework.data.domain.Pageable;

public interface BudgetService {
    BudgetResponse add(BudgetRequest budgetRequest);

    BudgetYearStatsResponse findAll(int year, String author, Pageable pageable);
}
