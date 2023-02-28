package com.wisecoach.fabric.security;

import com.wisecoach.security.userinfo.UserInfo;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/28 下午8:50
 * {@code @version:} 1.0.0
 */


public class UserInfoImpl implements UserInfo {

    private String username;
    private String password;

    public UserInfoImpl() {
    }

    public UserInfoImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
