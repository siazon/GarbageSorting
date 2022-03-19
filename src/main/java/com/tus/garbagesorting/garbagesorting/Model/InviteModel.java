package com.tus.garbagesorting.garbagesorting.Model;

public class InviteModel {
    private int id;
    private int user_id;
    private int inviter_id;
    private String invite_code;
    private String invite_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getInviter_id() {
        return inviter_id;
    }

    public void setInviter_id(int inviter_id) {
        this.inviter_id = inviter_id;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getInvite_time() {
        return invite_time;
    }

    public void setInvite_time(String invite_time) {
        this.invite_time = invite_time;
    }

    public InviteModel(int id, int user_id, int inviter_id, String invite_code, String invite_time) {
        this.id = id;
        this.user_id = user_id;
        this.inviter_id = inviter_id;
        this.invite_code = invite_code;
        this.invite_time = invite_time;
    }

}
