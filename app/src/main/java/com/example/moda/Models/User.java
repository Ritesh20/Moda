package com.example.moda.Models;

public class User {

    private long contact_number;
    private String email;
    private String userid;
    private String username;

    public User(long contact_number, String email, String userid, String username) {
        this.contact_number = contact_number;
        this.email = email;
        this.userid = userid;
        this.username = username;
    }

    public User() {

    }

    public long getContact_number() {
        return contact_number;
    }

    public void setContact_number(long contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "contact_number='" + contact_number + '\'' +
                ", email='" + email + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
