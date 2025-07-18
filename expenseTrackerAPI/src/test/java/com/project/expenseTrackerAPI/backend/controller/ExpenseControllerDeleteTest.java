package com.project.expenseTrackerAPI.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ExpenseControllerDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void idNotFoundCheck() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/expense/100000000")
                        .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Item Not Found in Database"));
    }

    @Test
    void idNotNumberCheck() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/expense/a")
                        .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Incorrect Path Variables"));
    }
}