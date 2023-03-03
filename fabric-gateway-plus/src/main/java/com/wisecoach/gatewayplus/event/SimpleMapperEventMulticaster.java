package com.wisecoach.gatewayplus.event;

import java.util.LinkedHashSet;

/**
 * 默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午7:21
 * {@code @version:} 1.0.0
 */

public class SimpleMapperEventMulticaster implements MapperEventMulticaster {

    LinkedHashSet<MapperEventListener<?>> listeners = new LinkedHashSet<>();

    @Override
    public void addListener(MapperEventListener<?> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(MapperEventListener<?> listener) {
        listeners.remove(listener);
    }

    @Override
    public void clearListener() {
        listeners.clear();
    }

    @Override
    public void multicastEvent(MapperEvent event) {
        listeners.forEach(listener -> invokeListener(listener, event));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void invokeListener(MapperEventListener listener, MapperEvent event) {
        listener.onEvent(event);
    }

}
