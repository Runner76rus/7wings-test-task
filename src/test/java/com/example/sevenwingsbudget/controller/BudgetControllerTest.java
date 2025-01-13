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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestcontainersConfiguration.class)
@AutoConfigureMockMvc
class BudgetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetMapper budgetMapper;

    @BeforeEach
    void setUp() {
        budgetRepository.deleteAll();
    }

//    addRecord(new Budget(2020, 5, 100, BudgetType.Приход));
//    addRecord(new Budget(2020, 1, 5, BudgetType.Приход));
//    addRecord(new Budget(2020, 5, 50, BudgetType.Приход));
//    addRecord(new Budget(2020, 1, 30, BudgetType.Приход));
//    addRecord(new Budget(2020, 5, 400, BudgetType.Приход));

//    addRecord(BudgetRecord(2020, 5, 10, BudgetType.Приход))
//    addRecord(BudgetRecord(2020, 5, 5, BudgetType.Приход))
//    addRecord(BudgetRecord(2020, 5, 20, BudgetType.Приход))
//    addRecord(BudgetRecord(2020, 5, 30, BudgetType.Приход))
//    addRecord(BudgetRecord(2020, 5, 40, BudgetType.Приход))
//    addRecord(BudgetRecord(2030, 1, 1, BudgetType.Расход))


    @Test
    void testBudgetPagination() throws Exception {
        addRecord(new Budget(2020, 5, 10, BudgetType.Приход));
        addRecord(new Budget(2020, 5, 5, BudgetType.Приход));
        addRecord(new Budget(2020, 5, 20, BudgetType.Приход));
        addRecord(new Budget(2020, 5, 30, BudgetType.Приход));
        addRecord(new Budget(2020, 5, 40, BudgetType.Приход));
        addRecord(new Budget(2030, 1, 1, BudgetType.Приход));

        mockMvc.perform(get("/budget/year/2020/stats")
                .param("size","3")
                .param("offset","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        status().isOk(),
                        jsonPath("$.total").value(5),
                        jsonPath("$.items.size()").value(3),
                        jsonPath("$.totalByType['Приход']").value(105)
                );
    }

    private void addRecord(Budget record){
        budgetRepository.save(record);
    }
}