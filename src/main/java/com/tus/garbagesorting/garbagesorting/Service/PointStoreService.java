package com.tus.garbagesorting.garbagesorting.Service;

import com.tus.garbagesorting.garbagesorting.Common.SnowflakeIdWorker;
import com.tus.garbagesorting.garbagesorting.Mapper.PointStoreMapper;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PointStoreService implements PointStoreMapper {

    public User user;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String GetGiftList(int user_id) {
        long snowid = new SnowflakeIdWorker(0, 0).nextId();
        String code = String.valueOf(snowid);
        int res = jdbcTemplate.update(
                "insert into tb_invite (user_id, invite_code) " +
                        "values(?,?)", user_id, code);
        if (res > 0)
            return code;
        else
            return "";
    }


}

