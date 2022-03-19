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

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}


