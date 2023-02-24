package com.wisecoach.security.userinfo;

/**
 * 默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:18
 * {@code @version:} 1.0.0
 */

public class UserInfoContextImpl implements UserInfoContext {

    private UserInfo userInfo;

    @Override
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
