package com.ontariotechu.sofe3980U;

// Importing necessary testing and Spring framework libraries
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Annotating the test class to specify that it should run with SpringRunner
@RunWith(SpringRunner.class)
// Telling Spring that we want to test the HelloAPIController class
@WebMvcTest(HelloAPIController.class)
public class HelloAPIControllerTest {

    // Autowiring MockMvc to simulate HTTP requests
    @Autowired
    private MockMvc mvc;

    // Test method for the /helloAPI endpoint with no parameters
    @Test
    public void helloAPINoParameter() throws Exception {
        // Perform a GET request to /helloAPI without any parameters
        this.mvc.perform(get("/helloAPI"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(content().string("Hello World!"));  // Verify the response content is "Hello World!"
    }

    // Test method for the /helloAPI endpoint with a name parameter
    @Test
    public void helloAPIWithName() throws Exception {
        // Perform a GET request to /helloAPI with a "name" parameter set to "John"
        this.mvc.perform(get("/helloAPI").param("name", "John"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(content().string("Hello John!"));  // Verify the response content is "Hello John!"
    }

    // Test method for the /emailAPI endpoint with no parameters
    @Test
    public void EmailAPINoParameters() throws Exception {
        // Perform a GET request to /emailAPI without any parameters
        this.mvc.perform(get("/emailAPI"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))  // Verify "name" field in JSON response
                .andExpect(MockMvcResultMatchers.jsonPath("$.suggestedEmail").value("John.Doe@OntarioTechU.net"));  // Verify "suggestedEmail" field in JSON response
    }

    // Test method for the /emailAPI endpoint with a first name parameter
    @Test
    public void EmailAPIWithFirstName() throws Exception {
        // Perform a GET request to /emailAPI with a "fname" parameter set to "Jack"
        this.mvc.perform(get("/emailAPI").param("fname", "Jack"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jack Doe"))  // Verify "name" field in JSON response
                .andExpect(MockMvcResultMatchers.jsonPath("$.suggestedEmail").value("Jack.Doe@OntarioTechU.net"));  // Verify "suggestedEmail" field in JSON response
    }

    // Test method for the /emailAPI endpoint with a last name parameter
    @Test
    public void EmailAPIWithLastName() throws Exception {
        // Perform a GET request to /emailAPI with a "lname" parameter set to "Sparrow"
        this.mvc.perform(get("/emailAPI").param("lname", "Sparrow"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Sparrow"))  // Verify "name" field in JSON response
                .andExpect(MockMvcResultMatchers.jsonPath("$.suggestedEmail").value("John.Sparrow@OntarioTechU.net"));  // Verify "suggestedEmail" field in JSON response
    }

    // Test method for the /emailAPI endpoint with both first and last name parameters
    @Test
    public void EmailAPIWithFullName() throws Exception {
        // Perform a GET request to /emailAPI with "fname" set to "Jack" and "lname" set to "Sparrow"
        this.mvc.perform(get("/emailAPI").param("fname", "Jack").param("lname", "Sparrow"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jack Sparrow"))  // Verify "name" field in JSON response
                .andExpect(MockMvcResultMatchers.jsonPath("$.suggestedEmail").value("Jack.Sparrow@OntarioTechU.net"));  // Verify "suggestedEmail" field in JSON response
    }
}
