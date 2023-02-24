package com.wisecoach.security.user;

import com.wisecoach.security.userinfo.UserInfo;

/**
 * {@link UserProvider} 的管理器，会以一定方式调度注册在内的UserProvider去获取 {@link User}
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 上午11:06
 * {@code @version:} 1.0.0
 */

public interface UserProviderManager {
    /**
     * 调度注册在内的UserProvider获取User
     * @param userInfo 用户信息
     * @return user
     */
    User getUser(UserInfo userInfo);

    /**
     * 使用特定类型的userProviders去获取User
     * @param userInfo 用户信息
     * @param classes 特定的provider类
     * @return user
     */
    User getUser(UserInfo userInfo, Class<? extends UserProvider>[] classes);

    /**
     * 取得当前注册在内的Providers
     * @return providers
     */
    Iterable<UserProvider> getProviders();

    /**
     * 注册一个userProvider
     * @param userProvider 要注册的userProvider
     */
    void register(UserProvider userProvider);
}
