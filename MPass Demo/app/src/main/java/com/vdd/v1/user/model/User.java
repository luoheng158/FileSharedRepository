// Generated by HybirdPB CodeGenerator(1.2.0). DO NOT EDIT!
// Generation date is 2020-06-14 14:29:32

package com.vdd.v1.user.model;


public class User {
    public Long userId;
    public Long timestamp;
    public String username;
    public String password;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", timestamp=" + timestamp +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}