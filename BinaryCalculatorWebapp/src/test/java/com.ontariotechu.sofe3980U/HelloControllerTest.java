package com.ontariotechu.sofe3980U;

// Importing necessary testing and Spring framework libraries
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Annotating the test class to specify that it should run with SpringRunner
@RunWith(SpringRunner.class)
// Telling Spring that we want to test the HelloController class
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    // Autowiring MockMvc to simulate HTTP requests
    @Autowired
    private MockMvc mvc;

    // Test method to check the default behavior when accessing the /hello endpoint
    @Test
    public void getDefault() throws Exception {
        // Perform a GET request to the /hello URL
        this.mvc.perform(get("/hello"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(view().name("hello"))  // Ensure the view name is "hello"
                .andExpect(model().attribute("name", "World"));  // Ensure the "name" attribute in the model is "World"
    }

    // Test method to check the behavior when a "name" parameter is passed in the GET request
    @Test
    public void helloWithName() throws Exception {
        // Perform a GET request to /hello with a query parameter "name" set to "Doe"
        this.mvc.perform(get("/hello?name=Doe"))
                .andExpect(status().isOk())  // Check if the response status is 200 OK
                .andExpect(view().name("hello"))  // Ensure the view name is "hello"
                .andExpect(model().attribute("name", "Doe"));  // Ensure the "name" attribute in the model is "Doe"
    }
}
