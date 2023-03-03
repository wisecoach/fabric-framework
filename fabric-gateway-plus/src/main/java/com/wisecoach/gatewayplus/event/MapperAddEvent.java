package com.wisecoach.gatewayplus.event;

/**
 * Mapper接口在被加入到MapperRegistry中时，会触发该事件
 * @see com.wisecoach.gatewayplus.proxy.MapperRegistry
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午6:43
 * {@code @version:} 1.0.0
 */


public class MapperAddEvent extends MapperEvent {

    /**
     * @param mapperInterface 加入的MapperInterface
     */
    public MapperAddEvent(Class<?> mapperInterface) {
        super(mapperInterface);
    }

    public Class<?> getMapperInterface() {
        return (Class<?>) getSource();
    }
}
