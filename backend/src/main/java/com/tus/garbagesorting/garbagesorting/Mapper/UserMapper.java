package com.tus.garbagesorting.garbagesorting.Mapper;

import com.tus.garbagesorting.garbagesorting.Model.User;

import java.util.List;



public interface UserMapper {
    int insert(User user);

    int update(User user);

    int deleteById(int id);

    List<User> findAll();
}
