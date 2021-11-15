package me.hyunsoo.springbootmvc.user;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"));
    }

    @Test
    public void createUser_JSON() throws Exception{

        String userJson = "{\"name\" : \"hyunsoo\", \"age\" : \"29\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_XML_VALUE)
                        .content(userJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(Matchers.equalTo("hyunsoo"))))
         .andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(Matchers.equalTo(29))))
         .andExpect(MockMvcResultMatchers.status().is(200))
         .andDo(MockMvcResultHandlers.print());




    }
}