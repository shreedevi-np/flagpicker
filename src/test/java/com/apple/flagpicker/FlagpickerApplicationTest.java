package com.apple.flagpicker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FlagPickerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "file:${classpath}/../src/test/resources/application.test.properties")
public class FlagpickerApplicationTest {

    public final static String API_PREFIX = "/api/v1";
    
    @Autowired
    public MockMvc controller;

    
    @Test
    public void contextLoads() throws Exception {
        this.controller.perform(get("/api/v1/continents/America")).andExpect(status().is4xxClientError());
    }
}
