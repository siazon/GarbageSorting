package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Mapper.UserMapper;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
//    @Qualifier("UserService")              // Test JdbcTemplate
    private UserMapper userMapper;

    @RequestMapping("/AllUsers")
    public Object findAll(){
      List<User> list=  userMapper.findAll();
        return list;
    }

    @RequestMapping("/InsertUser")
    public Object insert(@RequestBody User user){


        int res=  userMapper.insert(user);
        return "Account successfully created";
    }

    @RequestMapping("/Login")
    public Object login(@RequestParam String username){

        List<User> list=  userMapper.findAll();

//        list res=  userMapper.findAll();
        return username;
    }

    // Create Api to check if email is already in use
}
