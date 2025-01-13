package com.example.sevenwingsbudget.controller;

import com.example.sevenwingsbudget.dto.request.BudgetRequest;
import com.example.sevenwingsbudget.dto.response.BudgetResponse;
import com.example.sevenwingsbudget.dto.response.BudgetYearStatsResponse;
import com.example.sevenwingsbudget.service.BudgetService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public BudgetResponse save(BudgetRequest budget) {
        return budgetService.add(budget);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/year/{year}/stats")
    public BudgetYearStatsResponse getYearStats(@PathVariable @Min(1900)
                                                int year,
                                                @RequestParam(required = false) String author,
                                                Pageable pageable) {
        return budgetService.findAll(year, author, pageable);
    }
}
