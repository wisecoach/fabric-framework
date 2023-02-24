package com.wisecoach.security.userinfo;

import com.wisecoach.annotation.NotNull;

/**
 * {@link UserInfoContextHolder} 的策略，对应可以采用不同的方式去实现存储UserInfoContext
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:22
 * {@code @version:} 1.0.0
 */

public interface UserInfoContextHolderStrategy {
    /**
     * 设置当前context
     * @param context
     */
    void setUserInfoContext(UserInfoContext context);

    /**
     * 取得当前context
     * @return context
     */
    @NotNull
    UserInfoContext getUserInfoContext();

    /**
     * 清除当前的context
     */
    void clearUserInfoContext();

    /**
     * 取得空的UserInfoContext
     * @return context
     */
    UserInfoContext getEmptyUserInfoContext();
}
