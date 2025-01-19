package com.example.sevenwingsbudget.service.impl;

import com.example.sevenwingsbudget.dto.request.BudgetRequest;
import com.example.sevenwingsbudget.dto.response.BudgetResponse;
import com.example.sevenwingsbudget.dto.response.BudgetYearStatsResponse;
import com.example.sevenwingsbudget.exception.NotFoundException;
import com.example.sevenwingsbudget.mapper.BudgetMapper;
import com.example.sevenwingsbudget.model.Author;
import com.example.sevenwingsbudget.model.Budget;
import com.example.sevenwingsbudget.model.TypeAmountProjection;
import com.example.sevenwingsbudget.repository.AuthorRepository;
import com.example.sevenwingsbudget.repository.BudgetRepository;
import com.example.sevenwingsbudget.service.BudgetService;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final AuthorRepository authorRepository;
    private final BudgetMapper budgetMapper;

    public BudgetResponse add(BudgetRequest budgetRequest) {
        Author author = null;
        long authorId = budgetRequest.getAuthorId();
        if (authorId > 0) {
            author = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Author id not found"));
        }
        Budget budget = budgetMapper.toBudget(budgetRequest, author);

        return budgetMapper.toBudgetResponse(budgetRepository.save(budget));
    }

    @Transactional(readOnly = true)
    public BudgetYearStatsResponse findAll(int year, String author, Pageable pageable) {
        Specification<Budget> spec = Specification.where(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("year"), year)
        );

        if (StringUtils.isNotBlank(author)) {
            spec = spec.and(
                    (root, query, criteriaBuilder) -> {
                        Join<Budget, Author> authorJoin = root.join("author");
                        return criteriaBuilder.like(authorJoin.get("name"), "%" + author.toLowerCase() + "%");
                    }
            );
        }

        pageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.asc("month"), Sort.Order.desc("amount")));

        Page<Budget> page = budgetRepository.findAll(spec, pageable);
        List<TypeAmountProjection>  statistic = budgetRepository.findTotalStatisticByBudgetType(year);

        return budgetMapper.toBudgetYearsResponseStats(page,statistic);
    }
}
