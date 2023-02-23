package com.wisecoach.security.userprovider;

import com.wisecoach.security.user.User;
import com.wisecoach.security.userinfo.UserInfo;

/**
 * {@link UserProvider} 的管理器，会以一定方式调度注册在内的UserProvider去获取 {@link User}
 * @author: wisecoach
 * @date: 2023/2/23 上午11:06
 * @version: 1.0.0
 */

public interface UserProviderManager {
    /**
     * 调度注册在内的UserProvider获取User
     * @param userInfo 用户信息
     * @return user
     */
    User getUser(UserInfo userInfo);

    /**
     * 取得当前注册在内的Providers
     * @return providers
     */
    Iterable<UserProvider> getProviders();


}
