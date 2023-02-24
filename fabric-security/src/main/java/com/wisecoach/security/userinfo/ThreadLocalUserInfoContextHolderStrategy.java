package com.wisecoach.security.userinfo;

import com.wisecoach.util.Assert;

/**
 * 使用ThreadLocal存储UserInfoContext，保证每个处理请求的线程各自存储其UserInfoContext
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午2:28
 * {@code @version:} 1.0.0
 */


public class ThreadLocalUserInfoContextHolderStrategy implements UserInfoContextHolderStrategy {

    private static final ThreadLocal<UserInfoContext> contextHolder = new ThreadLocal<>();

    @Override
    public void setUserInfoContext(UserInfoContext context) {
        Assert.notNull(context, "不可存储空的context");
        contextHolder.set(context);
    }

    @Override
    public UserInfoContext getUserInfoContext() {
        UserInfoContext context = contextHolder.get();
        if (context == null) {
            context = getEmptyUserInfoContext();
            contextHolder.set(context);
        }
        return context;
    }

    @Override
    public void clearUserInfoContext() {
        contextHolder.remove();
    }

    @Override
    public UserInfoContext getEmptyUserInfoContext() {
        return new UserInfoContextImpl();
    }
}
