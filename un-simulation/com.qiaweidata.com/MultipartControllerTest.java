package com.fly.controller;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/spring-mvc.xml"})
public class MultipartControllerTest
{
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        log.info("★★★★★★★★ WebApplicationContext = {}", wac);
    }
    
    /**
     * 测试 RestAPI
     * 
     * @throws Exception
     * 
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testRestAPI()
        throws Exception
    {
        // get、post
        mockMvc.perform(MockMvcRequestBuilders.get("/get")).andDo(MockMvcResultHandlers.print());
        
        String path = MultipartControllerTest.class.getResource("/123.jpg").getPath();
        InputStream is = new FileSystemResource(path).getInputStream();
        MockMultipartFile file = new MockMultipartFile("file", "18.jpg", MediaType.MULTIPART_FORM_DATA_VALUE, is);
        
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/post").file(file).param("name", "girl").param("age", "18")).andDo(MockMvcResultHandlers.print());
    }
}
