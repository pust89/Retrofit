package com.pustovit.postapp.model;

/**
 * Created by Pustovit Vladimir on 06.01.2020.
 * vovapust1989@gmail.com
 */

public class User {
    private String userEmail;
    private String password;
    private int id;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
