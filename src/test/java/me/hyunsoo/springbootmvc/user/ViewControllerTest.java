package me.hyunsoo.springbootmvc.user;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class ViewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void viewTest() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/view/main"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name(Matchers.is("main")))
                .andExpect(MockMvcResultMatchers.model().attribute("name", "hyunsoo"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

       User user =  (User)result.getModelAndView().getModel().getOrDefault("user", null);
       if(user != null){
           System.out.println(user);
       }

    }

}