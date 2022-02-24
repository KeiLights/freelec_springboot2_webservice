package com.kwang.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void hello가_리턴된다() throws Exception {
        //given
        String hello = "hello";
        //when

        //then
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
     }

     @Test
     void helloDto가_리턴된다() throws Exception {
         //given
         String name = "hello";
         int amount = 1000;
         //when

         //then
         mvc.perform(
                         get("/hello/dto")
                                 .param("name", name)
                                 .param("amount", String.valueOf(amount)))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.name").value(name))
                 .andExpect(jsonPath("$.amount").value(amount))
                 .andExpect(jsonPath("$.amount", is(amount)));
      }
}