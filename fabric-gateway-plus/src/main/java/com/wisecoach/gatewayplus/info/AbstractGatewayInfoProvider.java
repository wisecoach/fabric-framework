package com.wisecoach.gatewayplus.info;

import java.lang.reflect.Method;

/**
 * 一个实现了 {@link GatewayInfoProvider} 的抽象实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午2:40
 * {@code @version:} 1.0.0
 */

public abstract class AbstractGatewayInfoProvider implements GatewayInfoProvider {

    /**
     * 主要帮助用户自动将GatewayInfo放入了GatewayInfoContextHolder
     * @param obj 执行被加强方法的对象
     * @param method 被加强的方法
     * @param args 方法参数
     * @return
     */
    @Override
    public GatewayInfo getGatewayInfo(Object obj, Method method, Object[] args) {
        GatewayInfoContext context = GatewayInfoContextHolder.getContext();
        GatewayInfo gatewayInfo = context.getGatewayInfo();
        if (gatewayInfo == null) {
            gatewayInfo = fetchGatewayInfo(obj, method, args);
            context.setGatewayInfo(gatewayInfo);
        }
        return gatewayInfo;
    }

    /**
     * 这个抽象方法要求用户自定义实现获得GatewayInfo
     * @param obj 执行被加强方法的对象
     * @param method 被加强的方法
     * @param args 方法参数
     * @return GatewayInfo
     */
    protected abstract GatewayInfo fetchGatewayInfo(Object obj, Method method, Object[] args);
}
