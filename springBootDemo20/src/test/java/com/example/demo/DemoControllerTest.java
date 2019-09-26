package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTest
{
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testBlogs() throws Exception
    {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/Blogs")).andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    @Test
    public void testCreate() throws Exception
    {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("Title", "title11");
        params.add("Content", "content22");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/Blog").params(params)).andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    @Test
    public void testUpdate() throws Exception
    {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "11");
        params.add("Title", "title11");
        params.add("Content", "content22");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/Blog").params(params)).andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    @Test
    public void testPatch() throws Exception
    {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "11");
        params.add("Content", "content22");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/Blog/Content").params(params)).andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    @Test
    public void testGet() throws Exception
    {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/Blog/66")).andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }

    @Test
    public void testDelete() throws Exception
    {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/Blog/99")).andReturn().getResponse().getContentAsString();
        System.out.println("Result === " + mvcResult);
    }
}
