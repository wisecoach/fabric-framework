package com.wisecoach.security.user;

import com.wisecoach.security.userinfo.UserInfo;

/**
 * User提供者，允许根据 {@link UserInfo} 来获取具体的 {@link User}
 * 具体使用受 {@link UserProviderManager} 管理
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 上午10:52
 * {@code @version:} 1.0.0
 */

public interface UserProvider {
    /**
     * 根据UserInfo获取User
     * @param userInfo 用户信息
     * @return user
     */
    User getUser(UserInfo userInfo);

    /**
     * 判断该UserProvider是否支持该UserInfo
     * @param userInfo 用户信息
     * @return 是否支持该类型的userInfo
     */
    boolean support(UserInfo userInfo);

    /**
     * 取得该provider的优先级，优先级越高的provider会优先被调度
     * @return priority
     */
    int getPriority();
}
