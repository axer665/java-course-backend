package ru.netology.cloudstorage.service;

import ru.netology.cloudstorage.model.AuthToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static ru.netology.cloudstorage.exceptions.MessageConstant.LOGIN;
import static ru.netology.cloudstorage.exceptions.MessageConstant.LOGIN_NOT_VALID_PASSWORD;
import static ru.netology.cloudstorage.service.PrepareInfoForTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    private AuthToken authToken;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginValidTest() throws Exception {
        when(authToken.getAuthToken()).thenReturn(TOKEN);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(LOGIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"login\": \"" + CORRECT_LOGIN + "\", \"password\": \"" + VALID_PASSWORD + "\"}");
        String authToken = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertNotNull(authToken);
        assertTrue(authToken.contains("auth-token"));
    }

    @Test
    public void loginNonValidTest() throws Exception {
        when(authToken.getAuthToken()).thenReturn(TOKEN);
        mockMvc.perform(MockMvcRequestBuilders.post(LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\": \"" + "check@email.ru" + "\", \"password\": \"" + NON_VALID_PASSWORD + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.content().string(LOGIN_NOT_VALID_PASSWORD));
    }
}