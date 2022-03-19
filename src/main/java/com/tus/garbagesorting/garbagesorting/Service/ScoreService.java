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
        return jdbcTemplate.update(
                "insert into scores(user_score, user_id)" +
                        "values(?, ?)",
                score.getUserScore(), score.getUserId());
    }

    @Override
    public int updateScore(Score score) {
        return jdbcTemplate.update("update scores set user_score=? where user_id = ?", score.getUserScore(),
                score.getUserId());
    }

    @Override
    public Score findScoreById(int id) {
        // query the user_score view which was created in the db, using the id.
        return jdbcTemplate.queryForObject("select * from user_score where user_id=?", new Object[]{id}, (rs, rowNum) ->
                new Score(
                        rs.getInt("user_score"),
                        rs.getInt("user_id"),
                        rs.getString("user_name")
                ));
    }

    @Override
    public List<Score> findAllScores() {
        return jdbcTemplate.query(
                "select tb_user.user_name, scores.user_id, scores.user_score from scores inner join tb_user on scores" +
                        ".user_id = tb_user.id order by user_score desc;",
                (rs, rowNum) ->
                        new Score(
                                rs.getInt("user_score"),
                                rs.getInt("user_id"),
                                rs.getString("user_name")
                        )
        );
    }
}
