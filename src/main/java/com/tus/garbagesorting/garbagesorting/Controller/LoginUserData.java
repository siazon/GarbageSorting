package com.tus.garbagesorting.garbagesorting.Controller;

// This class is created for the data provided in the request body during when a user logs in
public class LoginUserData {

    private String email;
    private String password;

    public LoginUserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
