package com.wisecoach.fabric.security;

import com.wisecoach.security.userinfo.AbstractUserInfoProvider;
import com.wisecoach.security.userinfo.UserInfo;

import java.lang.reflect.Method;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/28 下午8:49
 * {@code @version:} 1.0.0
 */


public class CustomUserInfoProvider extends AbstractUserInfoProvider {

    @Override
    protected UserInfo fetchUserInfo(Object obj, Method method, Object[] args) {
        return new UserInfoImpl("admin", "adminpw");
    }
}
