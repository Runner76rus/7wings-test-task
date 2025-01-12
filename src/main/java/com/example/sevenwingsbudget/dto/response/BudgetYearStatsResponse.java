package com.example.sevenwingsbudget.dto.response;

import com.example.sevenwingsbudget.model.Budget;

import java.util.List;
import java.util.Map;


public record BudgetYearStatsResponse(int total, Map<String,Integer> totalByType, List<Budget> items){}