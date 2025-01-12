package com.example.sevenwingsbudget;

import org.springframework.boot.SpringApplication;

public class TestSevenWingsBudgetApplication {

    public static void main(String[] args) {
        SpringApplication.from(SevenWingsBudgetApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
