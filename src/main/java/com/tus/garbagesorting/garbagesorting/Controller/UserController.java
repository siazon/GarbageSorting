

package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Mapper.UserMapper;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public User getUserById(@PathVariable int id) {

       return userMapper.findById(id);
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

            // Every admin should have an email which ends with "garbagesorting.com".
            if(user.getUser_email().contains("garbagesorting.com")) {
                user.setUser_role("admin");
            }
        }
        if (!isExist) {
            // if email doesn't already exist, add user
            userMapper.insert(user);
            //  return new ResponseEntity<>("Account successfully created", HttpStatus.OK);
            return user;
        }

        else
            return new ResponseEntity<>("Email already taken", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Login")
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
            if (email.equalsIgnoreCase(userEmail) && password.equals(userPassword)) {
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


    // Create Api to check if email is already in use
}

