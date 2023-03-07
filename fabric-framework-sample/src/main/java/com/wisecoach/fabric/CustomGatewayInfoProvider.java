package com.wisecoach.fabric;

import com.wisecoach.gatewayplus.info.AbstractGatewayInfoProvider;
import com.wisecoach.gatewayplus.info.GatewayInfo;
import com.wisecoach.gatewayplus.info.GatewayInfoImpl;
import com.wisecoach.security.user.User;
import com.wisecoach.security.user.UserContextHolder;

import java.lang.reflect.Method;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午1:01
 * {@code @version:} 1.0.0
 */


public class CustomGatewayInfoProvider extends AbstractGatewayInfoProvider {

    @Override
    protected GatewayInfo fetchGatewayInfo(Object obj, Method method, Object[] args) {
        User user = UserContextHolder.getContext().getUser();
        return new GatewayInfoImpl(user.getMspId(), user.getSigner(), user.getCertificate());
    }
}
