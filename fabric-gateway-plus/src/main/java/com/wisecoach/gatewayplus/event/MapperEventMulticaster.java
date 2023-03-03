package com.wisecoach.gatewayplus.event;

/**
 * 将分发 {@link MapperEvent} 和 管理 {@link MapperEventListener}的业务逻辑从 {@link MapperEventPublisher} 移到该接口，
 * 令{@link MapperEventPublisher}更注重业务本身
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午7:18
 * {@code @version:} 1.0.0
 */

public interface MapperEventMulticaster {
    void addListener(MapperEventListener<?> listener);
    void removeListener(MapperEventListener<?> listener);
    void clearListener();
    void multicastEvent(MapperEvent event);
}
