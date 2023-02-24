package com.wisecoach.security.userinfo;

/**
 * 封装 {@link UserInfo} 的，不是很清楚为什么要一层，抄spring-security的，可能是配合 UserInfoContextHolder的Strategy使用的
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:17
 * {@code @version:} 1.0.0
 */

public interface UserInfoContext {
    /**
     * 取得UserInfo
     * @return userInfo
     */
    UserInfo getUserInfo();

    /**
     * 设置UserInfo
     * @param userInfo 用户信息
     */
    void setUserInfo(UserInfo userInfo);
}
