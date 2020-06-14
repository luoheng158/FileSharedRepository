package com.example.DevDemo.model;

public class User {

    private long userId;
    private long timestamp;
    private String username;
    private String password;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        if (userId > 0) {
            sb.append("userId=" + userId);
            sb.append(", timestamp=" + timestamp);
        } else {
            sb.append("timestamp=" + timestamp);
        }
        sb.append(", username=" + username);
        sb.append(", password=" + password);
        sb.append("}");
        return sb.toString();
    }
}
