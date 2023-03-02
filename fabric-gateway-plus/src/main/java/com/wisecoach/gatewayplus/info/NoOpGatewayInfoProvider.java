package com.wisecoach.gatewayplus.info;

import java.lang.reflect.Method;

/**
 * 啥也不做的默认实现，主要是允许用户不使用该接口来取得GatewayInfo，
 * 而是通过 {@link GatewayInfoContextHolder#setContext(GatewayInfoContext)} 的方式直接存入GatewayInfo并取得
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午2:56
 * {@code @version:} 1.0.0
 */


public class NoOpGatewayInfoProvider extends AbstractGatewayInfoProvider {

    @Override
    protected GatewayInfo fetchGatewayInfo(Object obj, Method method, Object[] args) {
        return null;
    }
}
