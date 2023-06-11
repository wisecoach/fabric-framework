package com.wisecoach.gatewayplus.spring.mapper;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午3:41
 * {@code @version:} 1.0.0
 */

public class DisabledMapperFactoryBean<T> implements FactoryBean<T>, InitializingBean {
    private Class<T> mapperInterface;
    private final DisabledMapperProxy disabledMapperProxy = new DisabledMapperProxy();

    public DisabledMapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, disabledMapperProxy);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
