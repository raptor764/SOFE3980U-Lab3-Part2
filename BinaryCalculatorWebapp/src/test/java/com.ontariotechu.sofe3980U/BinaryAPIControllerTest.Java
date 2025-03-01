package com.ontariotechu.sofe3980U;

// Importing necessary testing and Spring framework libraries
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

// Annotating the test class to specify that it should run with SpringRunner
@RunWith(SpringRunner.class)
// Telling Spring that we want to test the BinaryAPIController class
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    // Autowiring MockMvc to simulate HTTP requests
    @Autowired
    private MockMvc mvc;

    // Test method for the /add endpoint that accepts binary operands and returns a sum
    @Test
    public void add() throws Exception {
        // Perform a GET request to /add with operands 111 and 1010
        this.mvc.perform(get("/add").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(content().string("10001")); // Check if the response content matches expected binary result
    }

    // Test method for the /add_json endpoint that returns a JSON response
    @Test
    public void add2() throws Exception {
        // Perform a GET request to /add_json with operands 111 and 1010
        this.mvc.perform(get("/add_json").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))  // Verify operand1 in JSON response
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010)) // Verify operand2 in JSON response
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))  // Verify the result in JSON response
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add")); // Verify the operator in JSON response
    }

    // Test method for the /multiply endpoint that multiplies binary numbers
    @Test
    public void multiply() throws Exception {
        // Perform a GET request to /multiply with operands 110 and 101
        this.mvc.perform(get("/multiply").param("operand1", "110").param("operand2", "101"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(content().string("11110")); // Check if the response content matches expected binary result for multiplication
    }

    // Test method for the /and endpoint that performs bitwise AND operation
    @Test
    public void bitwiseAnd() throws Exception {
        // Perform a GET request to /and with operands 1101 and 1011
        this.mvc.perform(get("/and").param("operand1", "1101").param("operand2", "1011"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(content().string("1001")); // Check if the response content matches expected binary result of bitwise AND
    }

    // Test method for the /or endpoint that performs bitwise OR operation
    @Test
    public void bitwiseOr() throws Exception {
        // Perform a GET request to /or with operands 1101 and 1011
        this.mvc.perform(get("/or").param("operand1", "1101").param("operand2", "1011"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(content().string("1111")); // Check if the response content matches expected binary result of bitwise OR
    }

}
