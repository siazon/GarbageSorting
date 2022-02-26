package com.tus.garbagesorting.garbagesorting.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tus.garbagesorting.garbagesorting.Mapper.UserMapper;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
class UserControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserMapper userMapper;

    User john = new User(1, "john@gmail.com", "john", "123654", "reredf", "registered", "test");
    User pete = new User(2, "pete@gmail.com", "pete", "135654", "reghbr", "unregistered", "test");

    @Test
    public void getAllUsersSuccessfully() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(john, pete));

        Mockito.when(userMapper.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/AllUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].user_name").value("pete"));
    }

    @Disabled
    @Test
    void insert() {
    }

    @Disabled
    @Test
    void login() {
    }

    @Disabled
    @Test
    void deleteUser() {
    }
}