package com.example.demo;

import com.example.demo.controller.testController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class WebControllerTest
{
    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(new testController()).build();
    }

    @Test
    public void test7() throws Exception
    {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/test7")).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void test8() throws Exception
    {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/test8").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }

}
