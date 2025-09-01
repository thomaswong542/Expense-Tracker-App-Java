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
class ExpenseControllerPutTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void missingFieldCheck() throws Exception{
        String jsonBody = "{\"date\":\"2025-05-01\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.put("/expense/1").content(jsonBody)
                        .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("some or all fields are missing"));
    }

    @Test
    void deserializationErrorCheck() throws Exception{
        String jsonBody = "{\"date\":\"2025-05-01\", " +
                "\"category\":\"categoryA\", " +
                "\"location\":\"cityA\", " +
                "\"shop\":\"shopA\", " +
                "\"item\":\"itemA\", " +
                "\"card\":\"cardA\", " +
                "\"quantity\":\"A\", " +
                "\"price\":12}";

        this.mockMvc.perform(MockMvcRequestBuilders.put("/expense/1").content(jsonBody)
                        .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Some types are incorrect in expenses fields"));
    }

    @Test
    void idNotFoundCheck() throws Exception{
        String jsonBody = "{\"date\":\"2025-05-01\", " +
                "\"category\":\"categoryA\", " +
                "\"location\":\"cityA\", " +
                "\"shop\":\"shopA\", " +
                "\"item\":\"itemA\", " +
                "\"card\":\"cardA\", " +
                "\"quantity\":1, " +
                "\"price\":12}";

        this.mockMvc.perform(MockMvcRequestBuilders.put("/expense/100000000").content(jsonBody)
                        .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Item Not Found in Database"));
    }

    @Test
    void idNotNumberCheck() throws Exception{
        String jsonBody = "{\"date\":\"2025-05-01\", " +
                "\"category\":\"categoryA\", " +
                "\"location\":\"cityA\", " +
                "\"shop\":\"shopA\", " +
                "\"item\":\"itemA\", " +
                "\"card\":\"cardA\", " +
                "\"quantity\":1, " +
                "\"price\":12}";

        this.mockMvc.perform(MockMvcRequestBuilders.put("/expense/a").content(jsonBody)
                        .header("Content-Type", "application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Incorrect Path Variables"));
    }
}