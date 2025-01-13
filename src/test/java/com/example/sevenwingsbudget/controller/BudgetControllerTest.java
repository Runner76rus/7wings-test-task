package com.example.sevenwingsbudget.controller;

import com.example.sevenwingsbudget.TestcontainersConfiguration;
import com.example.sevenwingsbudget.mapper.BudgetMapper;
import com.example.sevenwingsbudget.model.Budget;
import com.example.sevenwingsbudget.model.BudgetType;
import com.example.sevenwingsbudget.repository.BudgetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = TestcontainersConfiguration.class)
@AutoConfigureMockMvc
class BudgetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetMapper budgetMapper;

//    addRecord(BudgetRecord(2020, 5, 100, BudgetType.Приход))
//    addRecord(BudgetRecord(2020, 1, 5, BudgetType.Приход))
//    addRecord(BudgetRecord(2020, 5, 50, BudgetType.Приход))
//    addRecord(BudgetRecord(2020, 1, 30, BudgetType.Приход))
//    addRecord(BudgetRecord(2020, 5, 400, BudgetType.Приход))


    @BeforeEach
//    void setUp() {
//        budgetRepository.deleteAll();
//    }


    @Test
    void testBudgetPagination(){
        addRecord(new Budget(2020, 5, 100, BudgetType.Приход));
        addRecord(new Budget(2020, 1, 5, BudgetType.Приход));
        addRecord(new Budget(2020, 5, 50, BudgetType.Приход));
        addRecord(new Budget(2020, 1, 30, BudgetType.Приход));
        addRecord(new Budget(2020, 5, 400, BudgetType.Приход));
    }

    private void addRecord(Budget record){
        budgetRepository.save(record);
    }
}