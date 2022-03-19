package com.tus.garbagesorting.garbagesorting.Mapper;

import com.tus.garbagesorting.garbagesorting.Model.Score;
import com.tus.garbagesorting.garbagesorting.Model.User;

import java.util.List;

public interface ScoreMapper {
    int insertScore(Score score);
    int updateScore(Score score);
    Score findScorebById(int id);

    List<Score> findAllScores(); // use joins on the user table
}
