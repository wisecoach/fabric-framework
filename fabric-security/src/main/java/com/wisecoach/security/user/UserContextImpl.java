package com.wisecoach.security.user;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:18
 * {@code @version:} 1.0.0
 */

public class UserContextImpl implements UserContext {

    private User User;

    @Override
    public User getUser() {
        return User;
    }

    @Override
    public void setUser(User User) {
        this.User = User;
    }
}
