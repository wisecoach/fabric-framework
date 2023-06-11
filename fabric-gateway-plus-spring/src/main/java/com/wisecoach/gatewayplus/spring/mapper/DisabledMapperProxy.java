package com.wisecoach.gatewayplus.spring.mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/6/11 下午3:32
 * {@code @version:} 1.0.0
 */


public class DisabledMapperProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        return returnType.newInstance();
    }
}
