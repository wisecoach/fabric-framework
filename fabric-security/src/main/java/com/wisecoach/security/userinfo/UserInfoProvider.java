package com.wisecoach.security.userinfo;

import java.lang.reflect.Method;

/**
 * 用于给拦截器 取得UserInfo
 * 为了降低框架之间的依赖，设计成了由用户自己实现取得UserInfo的方式，不过只提供被加强方法的一些参数
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午2:35
 * {@code @version:} 1.0.0
 */

public interface UserInfoProvider {
    /**
     * 获取UserInfo
     * @param obj 执行被加强方法的对象
     * @param method 被加强的方法
     * @param args 方法参数
     * @return UserInfo
     */
    UserInfo getUserInfo(Object obj, Method method, Object[] args);
}
