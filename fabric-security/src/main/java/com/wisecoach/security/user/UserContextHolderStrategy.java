package com.wisecoach.security.user;

import com.wisecoach.annotation.NotNull;

/**
 * {@link UserContextHolder} 的策略，对应可以采用不同的方式去实现存储UserContext
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:22
 * {@code @version:} 1.0.0
 */

public interface UserContextHolderStrategy {
    /**
     * 设置当前context
     * @param context
     */
    void setUserContext(UserContext context);

    /**
     * 取得当前context
     * @return context
     */
    @NotNull
    UserContext getUserContext();

    /**
     * 清除当前的context
     */
    void clearUserContext();

    /**
     * 取得空的UserContext
     * @return context
     */
    UserContext getEmptyUserContext();
}
