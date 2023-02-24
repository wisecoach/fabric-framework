package com.wisecoach.security.user;

import com.wisecoach.util.Assert;

/**
 * 使用ThreadLocal存储UserContext，保证每个处理请求的线程各自存储其UserContext
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:28
 * {@code @version:} 1.0.0
 */


public class ThreadLocalUserContextHolderStrategy implements UserContextHolderStrategy {

    private static final ThreadLocal<UserContext> contextHolder = new ThreadLocal<>();

    @Override
    public void setUserContext(UserContext context) {
        Assert.notNull(context, "不可存储空的context");
        contextHolder.set(context);
    }

    @Override
    public UserContext getUserContext() {
        UserContext context = contextHolder.get();
        if (context == null) {
            context = getEmptyUserContext();
            contextHolder.set(context);
        }
        return context;
    }

    @Override
    public void clearUserContext() {
        contextHolder.remove();
    }

    @Override
    public UserContext getEmptyUserContext() {
        return new UserContextImpl();
    }
}
