package com.tus.garbagesorting.garbagesorting.Service;

import com.tus.garbagesorting.garbagesorting.Common.SnowflakeIdWorker;
import com.tus.garbagesorting.garbagesorting.Mapper.InviteMapper;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * This class implements the methods in the InviteMapper interface to perform
 * operations which generate and send invitation links to other users.
 **/

@Repository
public class InviteService implements InviteMapper {

    public User user;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String GetInviteCode(int user_id) {
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

