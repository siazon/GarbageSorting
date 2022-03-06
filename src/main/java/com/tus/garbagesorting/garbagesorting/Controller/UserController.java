

package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Mapper.UserMapper;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

// A test suite to test the API endpoint performing CRUD functionalities.

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/AllUsers")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userMapper.findAll();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @GetMapping("/GetUser/{id}")
    public User getUserById(@PathVariable int id) throws NotFoundException {
        return userMapper.findById(id);
//                if (userMapper.findById(id) != null) {
//                    throw new NotFoundException("User with Id " + id + " does not exist.");


    }


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

            // Create User Roles
            // Every admin should have an email which ends with "garbagesorting.com".
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
    public ResponseEntity<String> login(@RequestBody LoginUserData userData) {

        // Validate email and password
        if (userData.getEmail() == "" || userData.getPassword() == "") {
            return new ResponseEntity<>("username or password cannot be empty", HttpStatus.NOT_FOUND);
        }

        List<User> list = userMapper.findAll();

        // Check if email and password stored in database matches with email and password provided.
        String userEmail;
        String userPassword;
        boolean isExist = false;//user exist tag
        for (int i = 0; i < list.size(); i++) {
            userEmail = list.get(i).getUser_email();
            userPassword = list.get(i).getUser_password();
            if (userData.getEmail().equalsIgnoreCase(userEmail) && userData.getPassword().equals(userPassword)) {
                isExist = true;
            }
        }
        if (isExist)
            return new ResponseEntity<>("Successful login", HttpStatus.OK);
        else
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.NOT_FOUND);
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

