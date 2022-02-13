package com.tus.garbagesorting.garbagesorting.Service;

import com.tus.garbagesorting.garbagesorting.Mapper.UserMapper;
import com.tus.garbagesorting.garbagesorting.Model.tb_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class UserService implements UserMapper
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(tb_user user) {
        return jdbcTemplate.update(
                "insert into tb_user (user_name, user_email) values(?,?)",
                user.getUser_name(), user.getUser_email());
    }

    @Override
    public int update(tb_user user) {
        return jdbcTemplate.update(
                "update tb_user set user_name = ? where user_email = ?",
                user.getUser_name(), user.getUser_email());
    }

    @Override
    public int deleteById(int user_email) {
        return jdbcTemplate.update(
                "delete tb_user where user_email = ?",
                user_email);
    }

    @Override
    public List<tb_user> findAll() {
        return jdbcTemplate.query(
                "select * from tb_user",
                (rs, rowNum) ->
                        new tb_user(
                                rs.getString("user_email"),
                                rs.getString("user_name"),
                                rs.getString("user_phone"),
                                rs.getString("user_password"),
                                rs.getString("user_role"),
                                rs.getString("user_status")
                        )
        );
    }
}
