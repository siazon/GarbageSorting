package com.tus.garbagesorting.garbagesorting.Mapper;

import com.tus.garbagesorting.garbagesorting.Model.Score;
import com.tus.garbagesorting.garbagesorting.Model.User;

import java.util.List;

/**
 * This interface declares methods that are implemented in the ScoreService class
 * to create, update and find user scores.
 */


public interface ScoreMapper {
    int insertScore(Score score);
    int updateScore(Score score);
    Score findScoreById(int id);
    List<Score> findAllScores(); // use joins on the user table
}
