package com.example.sevenwingsbudget.repository;

import com.example.sevenwingsbudget.model.Budget;
import com.example.sevenwingsbudget.model.TypeAmountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>, JpaSpecificationExecutor<Budget> {

    @Query("SELECT b.type AS type, SUM(b.amount) AS totalAmount " +
            "FROM Budget b " +
            "WHERE :year = b.year " +
            "GROUP BY b.type")
    List<TypeAmountProjection> findTotalStatisticByBudgetType(int year);
}
