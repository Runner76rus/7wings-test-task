package com.example.sevenwingsbudget.mapper;

import com.example.sevenwingsbudget.dto.request.BudgetRequest;
import com.example.sevenwingsbudget.dto.response.BudgetResponse;
import com.example.sevenwingsbudget.dto.response.BudgetYearStatsResponse;
import com.example.sevenwingsbudget.model.Author;
import com.example.sevenwingsbudget.model.Budget;
import com.example.sevenwingsbudget.model.TypeAmountProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BudgetMapper {


    @Mapping(target = "createdAt",
            expression = "java(budget.getAuthor()!=null?budget.getAuthor().getCreatedAt().toString():null)")
    @Mapping(target = "fullName",
            expression = "java(budget.getAuthor()!=null?budget.getAuthor().getFullName():null)")
    BudgetResponse toBudgetResponse(Budget budget);

    @Mapping(target = "author", expression = ("java(author)"))
    Budget toBudget(BudgetRequest budgetRequest, Author author);

    default BudgetYearStatsResponse toBudgetYearsResponseStats(Page<Budget> page, List<TypeAmountProjection> statistic) {
        return new BudgetYearStatsResponse(page.getTotalElements(),
                statistic.stream()
                        .collect(Collectors.toMap(
                                TypeAmountProjection::getType,
                                TypeAmountProjection::getTotalAmount,
                                Integer::sum))
                , page.getContent().stream()
                .map(this::toBudgetResponse)
                .toList());
    }
}
