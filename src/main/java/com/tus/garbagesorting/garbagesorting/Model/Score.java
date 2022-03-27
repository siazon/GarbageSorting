package com.tus.garbagesorting.garbagesorting.Model;

/**
 * The Score class containing all the information intended to create a user's score, defined as instance variables.
 * It also contains getters and setter methods to retrieve and update a user's score.
 **/

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


