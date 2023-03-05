package com.wisecoach.gatewayplus.proxy;

import com.wisecoach.gatewayplus.bind.BindException;
import com.wisecoach.gatewayplus.config.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * ContractMapper对象注册器
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午6:45
 * {@code @version:} 1.0.0
 */


public class MapperRegistry {
    private final Configuration config;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> mapperInterface) {
        MapperProxyFactory<T> proxyFactory = (MapperProxyFactory<T>) knownMappers.get(mapperInterface);
        if (proxyFactory == null) {
            throw new BindException("目前该类型还没有注册到MapperRegistry：" + mapperInterface.getName());
        }
        return proxyFactory.newInstance(config);
    }

    public <T> boolean hasMapper(Class<T> mapperInterface) {
        return knownMappers.containsKey(mapperInterface);
    }

    public <T> void addMapper(Class<T> mapperInterface) {
        if (mapperInterface.isInterface()) {
            if (hasMapper(mapperInterface)) {
                throw new BindException("该类型已经注册到MapperRegistry：" + mapperInterface.getName());
            }
            knownMappers.put(mapperInterface, new MapperProxyFactory<>(mapperInterface));
        }
    }
}
