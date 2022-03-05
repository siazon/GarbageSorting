package com.tus.garbagesorting.garbagesorting.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tus.garbagesorting.garbagesorting.Mapper.UserMapper;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
class UserControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserMapper userMapper;

    User john = new User(1, "john@gmail.com", "john", "123654", "reredf", "player");
    User pete = new User(2, "pete@gmail.com", "pete", "135654", "reghbr", "admin");

    @Test
    public void getAllUsersSuccessfully() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(john, pete));

        Mockito.when(userMapper.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/AllUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].user_name", is("pete")));
        // .andExpect(jsonPath("$[1].user_name").value("pete"));
    }

    @Test
    void getUserByIdSuccessfully() throws Exception {
        Mockito.when(userMapper.findById(pete.getId())).thenReturn((pete));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/GetUser/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_name", is("pete")));

    }

    @Test
    void createdUserSuccessfully() throws Exception {
        User terry = new User(2, "terry@gmail.com", "terry", "49838294", "fhrhhd", "admin");

        Mockito.when(userMapper.insert(terry)).thenReturn(terry);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/InsertUser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(terry));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.user_name", is("terry")));

    }


    @Test
    void deleteUserSuccessfully() throws Exception {
        Mockito.when(userMapper.findById(pete.getId())).thenReturn((pete));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/delete/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}