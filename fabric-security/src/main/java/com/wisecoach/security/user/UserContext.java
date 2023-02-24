package com.wisecoach.security.user;


/**
 * 封装 {@link User} 的，不是很清楚为什么要一层，抄spring-security的，可能是配合 UserContextHolder的Strategy使用的
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:17
 * {@code @version:} 1.0.0
 */

public interface UserContext {
    /**
     * 取得User
     * @return User
     */
    User getUser();

    /**
     * 设置User
     * @param User 用户信息
     */
    void setUser(User User);
}
