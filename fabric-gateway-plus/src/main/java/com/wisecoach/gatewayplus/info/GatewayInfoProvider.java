package com.wisecoach.gatewayplus.info;

import java.lang.reflect.Method;

/**
 * 取得GatewayInfoInfo
 * 为了降低框架之间的依赖，设计成了由用户自己实现取得GatewayInfo的方式，不过只提供被加强方法的一些参数
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午8:39
 * {@code @version:} 1.0.0
 */

public interface GatewayInfoProvider {
    /**
     * 获取GatewayInfo
     * @param obj 执行被加强方法的对象
     * @param method 被加强的方法
     * @param args 方法参数
     * @return GatewayInfo
     */
    GatewayInfo getGatewayInfo(Object obj, Method method, Object[] args);
}
