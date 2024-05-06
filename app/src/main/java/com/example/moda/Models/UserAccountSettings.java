package com.example.moda.Models;

public class UserAccountSettings {

    private String bio;
    private long followers;
    private long following;
    private String fullname;
    private long posts;
    private String profile_picture;
    private String username;

    public UserAccountSettings(String bio, long followers, long following, String fullname, long posts, String profile_picture, String username) {
        this.bio = bio;
        this.followers = followers;
        this.following = following;
        this.fullname = fullname;
        this.posts = posts;
        this.profile_picture = profile_picture;
        this.username = username;
    }

    public UserAccountSettings() {
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserAccountSettings{" +
                "bio='" + bio + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", fullname='" + fullname + '\'' +
                ", posts=" + posts +
                ", profile_picture='" + profile_picture + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
