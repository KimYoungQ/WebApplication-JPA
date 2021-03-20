package com.webapplication.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("회원가입 화면 테스트")
    @Test
    public void joinForm() throws Exception {
        mockMvc.perform(get("/join"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/join"));
    }

    @DisplayName("회원가입 - 입력값 오류")
    @Test
    public void join_wrong_input() throws Exception {
        mockMvc.perform(post("/join")
                .param("name", "test*")
                .param("password", "123123"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/join"));
    }

    @DisplayName("회원가입 - 입력값 정상")
    @Test
    public void join_correct_input() throws Exception {
        mockMvc.perform(post("/join")
                .param("name", "testName")
                .param("password", "123123123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}