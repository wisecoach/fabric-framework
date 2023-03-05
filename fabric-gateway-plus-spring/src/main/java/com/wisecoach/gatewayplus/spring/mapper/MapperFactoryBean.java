package com.wisecoach.gatewayplus.spring.mapper;

import com.wisecoach.gatewayplus.proxy.MapperRegistry;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午3:41
 * {@code @version:} 1.0.0
 */

public class MapperFactoryBean<T> implements FactoryBean<T>, InitializingBean {
    private Class<T> mapperInterface;
    private MapperRegistry registry;

    public MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        return registry.getMapper(mapperInterface);
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

    public MapperRegistry getRegistry() {
        return registry;
    }

    public void setRegistry(MapperRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!registry.hasMapper(mapperInterface)) {
            registry.addMapper(mapperInterface);
        }
    }
}
