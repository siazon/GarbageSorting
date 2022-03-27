package com.tus.garbagesorting.garbagesorting.Mapper;

import com.tus.garbagesorting.garbagesorting.Model.User;

import java.util.List;

/***
 * This interface declares methods that are implemented in the UserService class
 * to create, find, update and delete a user
 */

public interface UserMapper {
    User insert(User user);

    int update(User user);

    int deleteById(int id);

    User findById(int id);

    User findByEmail(String email);

    List<User> findAll();
}
