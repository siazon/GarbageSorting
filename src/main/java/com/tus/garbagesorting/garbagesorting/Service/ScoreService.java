package com.tus.garbagesorting.garbagesorting.Service;

import com.tus.garbagesorting.garbagesorting.Mapper.ScoreMapper;
import com.tus.garbagesorting.garbagesorting.Model.Score;
import com.tus.garbagesorting.garbagesorting.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreService implements ScoreMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertScore(Score score) {
      return   jdbcTemplate.update(
                "insert into scores (user_score, user_id) " +
                        "values(?,?)",
               score.getUserScore());


    }

    @Override
    public int updateScore(Score score) {
        return jdbcTemplate.update("update scores set user_score=? where user_id = ?");
    }

    @Override
    public Score findScorebById(int user_id) {
//        return jdbcTemplate.queryForObject("select * from student_id = ?", studentRowMapper, studentId);
        return jdbcTemplate.queryForObject("SELECT * FROM scores WHERE id=?", new Object[]{user_id}, (rs, rowNum) ->
                new Score(
                        rs.getInt("user_score"),
                        rs.getInt("user_id")

                ));
    }

    @Override
    public List<Score> findAllScores() {
        return jdbcTemplate.query(
                "select * from scores inner join user on scores.user_id = tb_user.id order by user_score desc",
                (rs, rowNum) ->
                        new Score(
                                rs.getInt("user_score"),
                                rs.getInt("user_id")

                        )
        );
    }
}
