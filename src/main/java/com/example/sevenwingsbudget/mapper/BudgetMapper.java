package com.example.sevenwingsbudget.mapper;

import com.example.sevenwingsbudget.dto.request.BudgetRequest;
import com.example.sevenwingsbudget.dto.response.BudgetResponse;
import com.example.sevenwingsbudget.dto.response.BudgetYearStatsResponse;
import com.example.sevenwingsbudget.model.Author;
import com.example.sevenwingsbudget.model.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BudgetMapper {


    @Mapping(target = "createdAt", expression = "java(budget.getAuthor().getCreatedAt().toString())")
    @Mapping(target = "fullName", expression = "java(budget.getAuthor().getFullName())")
    BudgetResponse toBudgetResponse(Budget budget);

    @Mapping(target = "author", expression = ("java(author)"))
//    @Mapping(source = "budgetRequest.year", target = "year")
    Budget toBudget(BudgetRequest budgetRequest, Author author);

    default BudgetYearStatsResponse toBudgetYearsResponseStats(Page<Budget> page){
        return new BudgetYearStatsResponse(page.getTotalPages(),
                page.getContent().stream()
                        .collect(Collectors.toMap(b -> b.getType().name(), Budget::getAmount))
                , page.getContent());
    }
}
