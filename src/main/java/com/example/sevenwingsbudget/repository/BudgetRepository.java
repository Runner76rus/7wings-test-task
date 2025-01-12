package com.example.sevenwingsbudget.repository;

import com.example.sevenwingsbudget.model.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long>, JpaSpecificationExecutor<Budget> {

       Page<Budget> findByYear(int year, Pageable pageable);
}
