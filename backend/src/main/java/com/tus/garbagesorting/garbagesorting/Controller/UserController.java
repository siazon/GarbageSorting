package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Mapper.UserMapper;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
//    @Qualifier("UserService")              // Test JdbcTemplate
    private UserMapper userMapper;

    @RequestMapping("/AllUsers")
    public Object findAll() {
        List<User> list = userMapper.findAll();
        return list;
    }

    @RequestMapping("/InsertUser")
    public Object insert(@RequestBody User user) {

        int res = userMapper.insert(user);
        return "Account successfully created";
    }

    @RequestMapping("/Login")
    public Object login(@RequestParam String email, String password) {

        List<User> list = userMapper.findAll();

        // Validate user email and password.
        // Check if email and password stored in database matches email and password provided.

        String userEmail;
        String userPassword;

        for (int i = 0; i < list.size(); i++) {
            userEmail = list.get(i).getUser_email();
            userPassword = list.get(i).getUser_password();

            if (email.equalsIgnoreCase(userEmail) && password.equalsIgnoreCase(userPassword)) {
                return "Successful login";

            } else {
                return "Incorrect username or password";
            }
        }
        return -1;
    }

    // Create Api to check if email is already in use
}
