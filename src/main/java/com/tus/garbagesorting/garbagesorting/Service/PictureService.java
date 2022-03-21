package com.tus.garbagesorting.garbagesorting.Service;

import com.tus.garbagesorting.garbagesorting.Mapper.PictureMapper;
import com.tus.garbagesorting.garbagesorting.Model.PictureInfo;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PictureService implements PictureMapper {

    public User user;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int upset(PictureInfo pictureInfo) {
        return jdbcTemplate.update(
                "INSERT INTO tb_picture (path,`type`,sort_times) " +
                        "VALUES  (?,?,?)  " +
                        "ON DUPLICATE KEY UPDATE  `type` = VALUES(`type`),path = VALUES(path)," +
                        "sort_times = sort_times+VALUES (sort_times);",
                pictureInfo.getPath(), pictureInfo.getType(), pictureInfo.getSort_times());
    }


    @Override
    public User findByUrl(String url) {

        return jdbcTemplate.queryForObject("SELECT * FROM tb_user WHERE user_email=?", new Object[]{url}, (rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("user_email"),
                        rs.getString("user_name"),
                        rs.getString("user_phone"),
                        rs.getString("user_password"),
                        rs.getString("user_role")

                ));

    }

}




