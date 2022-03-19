package com.tus.garbagesorting.garbagesorting.Model;

public class Score {
    public int userScore;
    public int userId;
    public String userName;

    public Score(int userScore, int userId, String userName) {
        this.userScore = userScore;
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUser_name(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}


