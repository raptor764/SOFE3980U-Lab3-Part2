package com.ontariotechu.sofe3980U;

// Importing necessary testing and Spring framework libraries
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
// Telling Spring that we want to test the BinaryController class
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    // Autowiring MockMvc to simulate HTTP requests
    @Autowired
    private MockMvc mvc;

    // Test method to check the default behavior when the root URL is accessed
    @Test
    public void getDefault() throws Exception {
        // Perform a GET request to the root URL ("/")
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(view().name("calculator"))  // Ensure the view name is "calculator"
                .andExpect(model().attribute("operand1", ""))  // Check if operand1 is empty
                .andExpect(model().attribute("operand1Focused", false));  // Ensure operand1 is not focused
    }

    // Test method to check the behavior when a parameter (operand1) is passed in the GET request
    @Test
    public void getParameter() throws Exception {
        // Perform a GET request to the root URL with a parameter "operand1" set to "111"
        this.mvc.perform(get("/").param("operand1", "111"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(view().name("calculator"))  // Ensure the view name is "calculator"
                .andExpect(model().attribute("operand1", "111"))  // Check if operand1 is set to "111"
                .andExpect(model().attribute("operand1Focused", true));  // Ensure operand1 is focused
    }

    // Test method to check the behavior when POSTing parameters to the root URL
    @Test
    public void postParameter() throws Exception {
        // Perform a POST request to the root URL with operand1, operator, and operand2
        this.mvc.perform(post("/").param("operand1", "111").param("operator", "+").param("operand2", "111"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(view().name("result"))  // Ensure the view name is "result"
                .andExpect(model().attribute("result", "1110"))  // Verify the result attribute is "1110"
                .andExpect(model().attribute("operand1", "111"));  // Ensure operand1 is still "111"
    }

    // Test method to check the multiplication operation (POST request)
    @Test
    public void testMultiply() throws Exception {
        // Perform a POST request to the root URL with operands "110" and "101" and operator "*"
        this.mvc.perform(post("/").param("operand1", "110").param("operator", "*").param("operand2", "101"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(view().name("result"))  // Ensure the view name is "result"
                .andExpect(model().attribute("result", "11110"))  // Verify the result of multiplication is "11110"
                .andExpect(model().attribute("operand1", "110"));  // Ensure operand1 is "110"
    }

    // Test method to check the bitwise AND operation (POST request)
    @Test
    public void testBitwiseAnd() throws Exception {
        // Perform a POST request to the root URL with operands "1101" and "1011" and operator "&"
        this.mvc.perform(post("/").param("operand1", "1101").param("operator", "&").param("operand2", "1011"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(view().name("result"))  // Ensure the view name is "result"
                .andExpect(model().attribute("result", "1001"))  // Verify the result of bitwise AND is "1001"
                .andExpect(model().attribute("operand1", "1101"));  // Ensure operand1 is "1101"
    }

    // Test method to check the bitwise OR operation (POST request)
    @Test
    public void testBitwiseOr() throws Exception {
        // Perform a POST request to the root URL with operands "1101" and "1011" and operator "|"
        this.mvc.perform(post("/").param("operand1", "1101").param("operator", "|").param("operand2", "1011"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(view().name("result"))  // Ensure the view name is "result"
                .andExpect(model().attribute("result", "1111"))  // Verify the result of bitwise OR is "1111"
                .andExpect(model().attribute("operand1", "1101"));  // Ensure operand1 is "1101"
    }

}
