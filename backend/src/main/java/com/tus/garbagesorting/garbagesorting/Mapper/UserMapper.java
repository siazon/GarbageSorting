package com.tus.garbagesorting.garbagesorting.Mapper;

import com.tus.garbagesorting.garbagesorting.Model.tb_user;

import java.util.List;



public interface UserMapper {
    int insert(tb_user user);

    int update(tb_user user);

    int deleteById(int id);

    List<tb_user> findAll();
}
