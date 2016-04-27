package com.example.aylin.menulogin;

/**
 * Created by aylin on 22.04.2016.
 */

public class UserModel {
    public int id;
    public String username;
    public String password;
    public UserModel(int id, String username, String password) {
// TODO Auto-generated constructor stub
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserModel() {

    }
}