package com.tus.garbagesorting.garbagesorting.Model;


import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String user_email;
    private String user_name;
    private String user_phone;
    private String user_password;
    private String user_role;

    public User (int id, String user_email, String user_name, String user_phone, String user_password, String user_role) {
        this.id = id;
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_password = user_password;
        this.user_role = user_role;
    }

    public int getId() {
        return id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }


}
