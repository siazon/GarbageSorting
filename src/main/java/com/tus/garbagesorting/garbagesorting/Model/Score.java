package com.tus.garbagesorting.garbagesorting.Model;

public class Score {
    public int userScore;
    public int userId;
    public String user_name;

    public Score(int userScore, int userId, String user_name) {
        this.userScore = userScore;
        this.userId = userId;
        this.user_name = user_name;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}


