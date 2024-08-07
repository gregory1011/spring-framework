package com.cydeo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WelcomeController.class)
class WelcomeControllerTest {


    // integration testing below



    @Autowired
    private MockMvc mvc;

    @Test
    void welcome() throws Exception {

        // call /welcome endpoint
        // verify we get text the text in postman "welcome"

        RequestBuilder request = MockMvcRequestBuilders.get("/welcome")
                .accept(MediaType.APPLICATION_JSON);

        // verify
        MvcResult mvcResult = mvc.perform(request).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals("welcome", mvcResult.getResponse().getContentAsString());

    }


    @Test
    void welcome2() throws Exception {
        RequestBuilder request= MockMvcRequestBuilders.get("/welcome")
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("welcome"))
                .andReturn();

    }


}
