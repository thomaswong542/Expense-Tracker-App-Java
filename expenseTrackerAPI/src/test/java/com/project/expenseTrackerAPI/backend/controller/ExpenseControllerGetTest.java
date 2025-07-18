package com.project.expenseTrackerAPI.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// turn application.properties back to using dotenv
// write the remaining test
// check what to git ignore (especially env)

@SpringBootTest
@AutoConfigureMockMvc
class ExpenseControllerGetTest {

    // test case
    // 1. only startDate or endDate
    // 2. wrong date format
    // 3. startDate later than endDate
    // 4. wrong param key

    @Autowired
    private MockMvc mockMvc;

    @Test
    void wrongParamCheck() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/expense?a=12"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Field parameter is incorrect"));
    }

    @Test
    void onlyStartDateCheck() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/expense?startDate=2025-05-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("startDate and endDate must both exist or both not exist"));

    }

    @Test
    void onlyEndDateCheck() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/expense?endDate=2025-05-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("startDate and endDate must both exist or both not exist"));
    }

    @Test
    void wrongFormatCheck() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/expense?startDate=2025-aa111&endDate=2025-aa-112"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Wrong startDate and endDate format"));
    }

    @Test
    void wrongDateRangeCheck() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/expense?startDate=2025-05-01&endDate=2024-05-01"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("startDate cannot be after endDate"));

    }

}