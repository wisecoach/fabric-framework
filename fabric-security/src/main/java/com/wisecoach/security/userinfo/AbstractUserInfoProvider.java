package com.wisecoach.security.userinfo;

import java.lang.reflect.Method;

/**
 * 一个实现了 {@link UserInfoProvider} 的抽象实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午2:40
 * {@code @version:} 1.0.0
 */

public abstract class AbstractUserInfoProvider implements UserInfoProvider {

    /**
     * 主要帮助用户自动将UserInfo放入了UserInfoContextHolder
     * @param obj 执行被加强方法的对象
     * @param method 被加强的方法
     * @param args 方法参数
     * @return
     */
    @Override
    public UserInfo getUserInfo(Object obj, Method method, Object[] args) {
        UserInfoContext context = UserInfoContextHolder.getContext();
        UserInfo userInfo = context.getUserInfo();
        if (userInfo == null) {
            userInfo = fetchUserInfo(obj, method, args);
            context.setUserInfo(userInfo);
        }
        return userInfo;
    }

    /**
     * 这个抽象方法要求用户自定义实现获得UserInfo
     * @param obj 执行被加强方法的对象
     * @param method 被加强的方法
     * @param args 方法参数
     * @return UserInfo
     */
    protected abstract UserInfo fetchUserInfo(Object obj, Method method, Object[] args);
}
