package com.example.sevenwingsbudget.dto.response;

import java.util.List;
import java.util.Map;


public record BudgetYearStatsResponse(long total,
                                      Map<String, Integer> totalByType,
                                      List<BudgetResponse> items) {
}