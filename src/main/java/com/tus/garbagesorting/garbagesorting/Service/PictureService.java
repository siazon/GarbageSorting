package com.tus.garbagesorting.garbagesorting.Service;

import com.tus.garbagesorting.garbagesorting.Mapper.PictureMapper;
import com.tus.garbagesorting.garbagesorting.Model.PictureInfo;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<PictureInfo> findByPath(String path) {

        return jdbcTemplate.query("SELECT * FROM tb_picture WHERE path=?",
                new Object[]{path}, (rs, rowNum) ->
                        new PictureInfo(
                                0,
                                "",
                                rs.getString("type"),
                                rs.getString("path"),
                                rs.getInt("sort_times")
                        ));

    }

}




