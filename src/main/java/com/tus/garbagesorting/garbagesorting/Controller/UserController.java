package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Mapper.UserMapper;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
//    @Qualifier("UserService")              // Test JdbcTemplate
    private UserMapper userMapper;

    @RequestMapping("/AllUsers")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userMapper.findAll();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @RequestMapping("/InsertUser")
    public ResponseEntity<String> insert(@RequestBody User user) {

        int res = userMapper.insert(user);
        return new ResponseEntity<>("Account successfully created", HttpStatus.OK);
    }

    @RequestMapping("/Login")
    public ResponseEntity<String> login(@RequestParam String email, String password) {

        List<User> list = userMapper.findAll();

        // Validate user email and password.
        // Check if email and password stored in database matches email and password provided.
        String userEmail;
        String userPassword;
        boolean isExist = false;//user exist tag
        for (int i = 0; i < list.size(); i++) {
            userEmail = list.get(i).getUser_email();
            userPassword = list.get(i).getUser_password();
            if (email.equalsIgnoreCase(userEmail) && password.equalsIgnoreCase(userPassword)) {
                isExist = true;
            }
        }
        if (isExist)
            return new ResponseEntity<>("Successful login", HttpStatus.OK);
        else
            return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
    }

    // Create Api to check if email is already in use
}
