package com.wisecoach.gatewayplus.proxy;

import com.wisecoach.gatewayplus.config.GatewayPlusConfiguration;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MapperProxy工厂
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 上午11:30
 * {@code @version:} 1.0.0
 */


public class MapperProxyFactory<T> {
    /**
     * ContractMapper接口类
     */
    private final Class<T> mapperInterface;
    private final Map<Method, MapperProxy.MapperMethodInvoker> methodCached = new ConcurrentHashMap<>();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @SuppressWarnings("unchecked")
    public T newInstance(GatewayPlusConfiguration configuration) {
        MapperProxy<T> proxy = new MapperProxy<>(mapperInterface, methodCached, configuration);
        return  (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, proxy);
    }
}
