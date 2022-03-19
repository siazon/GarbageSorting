package com.tus.garbagesorting.garbagesorting.Model;

public class Score {
    public int userScore;
    public int scoreId;

    public Score(int userScore, int scoreID) {
        this.userScore = userScore;
        this. scoreId = scoreID;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}


