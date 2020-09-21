package com.tdd.calculadora.calcapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.calculadora.calcapp.services.CalculatorService;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class OperacionesControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CalculatorService calcService;

    @Test
    public void startCalc() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/poweron").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("powerOn"));
    }

    @Test
    @DisplayName("POST /sumar")
    public void sumar() throws Exception {
        /*sumar int*/
        JSONObject json = new JSONObject();
        json.put("num1", 1);
        json.put("num2", 2);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/sumar")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json.toString())
        )
        // Validate the response code and content type
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        //validate operation
        .andExpect(jsonPath("$.res", is(3)));
        
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
