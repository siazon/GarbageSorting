package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Common.JwtTokenUtil;
import com.tus.garbagesorting.garbagesorting.Mapper.UserMapper;
import com.tus.garbagesorting.garbagesorting.Model.LoginUserData;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains all REST enpoints to create, read, update, delete and login a user.
 * */

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // Get all users
    @GetMapping("/AllUsers")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userMapper.findAll();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    // Get one user by user id
    @GetMapping("/GetUser/{id}")
    public User getUserById(@PathVariable int id) throws NotFoundException {
        return userMapper.findById(id);
//                if (userMapper.findById(id) != null) {
//                    throw new NotFoundException("User with Id " + id + " does not exist.");
    }


    // Create a new user
    @PostMapping("/InsertUser")
    public Object insert(@RequestBody User user) {
        // Check if user email is already in use
        List<User> list = userMapper.findAll();
        String userEmail;

        boolean isExist = false;
        for (int i = 0; i < list.size(); i++) {
            userEmail = list.get(i).getUser_email();
            if (user.getUser_email().equalsIgnoreCase(userEmail)) {
                isExist = true;
            }

            // Create User Roles. Every admin will have the email extension - "garbagesorting.com".
            if (user.getUser_email().contains("garbagesorting.com")) {
                user.setUser_role("admin");
            } else {
                user.setUser_role("player");
            }
        }
        if (!isExist) {
            // if email doesn't already exist, add user
            userMapper.insert(user);
            return user;
        } else
            return new ResponseEntity<>("Email already taken", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUserData userData) {

        Map<String, Object> map = new HashMap<>();
        User userDB = null;
        try {
            userDB = userMapper.findByEmail(userData.getEmail());
        } catch (Exception ex) {
            map.put("state", false);
            map.put("msg", "username or password does not exist");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        // Validate email and password
        if (userData.getEmail() == "" || userData.getPassword() == "") {
            map.put("state", false);
            map.put("msg", "username or password cannot be empty");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }

        // Check if email and password stored in database matches with email and password provided.
        if (userDB.getUser_password().equals(userData.getPassword())) {
            Map<String, String> payload = new HashMap<>();
            payload.put("id", String.valueOf(userDB.getId()));
            payload.put("username", userDB.getUser_name());
            String token = JwtTokenUtil.getToken(payload);
            map.put("state", true);
            map.put("msg", "Successful login");// back token
            map.put("token", token);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } else {
            map.put("state", true);
            map.put("msg", "Incorrect username or password");// back token
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
//        if (userMapper.findById(id).isEmpty()) {
//            throw new NotFoundException("Patient with ID " + patientId + " does not exist.");
//        }
        userMapper.deleteById(id);
        return new ResponseEntity<>("User with id " + id + " deleted", HttpStatus.OK);

    }

}

