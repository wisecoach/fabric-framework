package com.wisecoach.gatewayplus.event;

import java.util.LinkedHashSet;

/**
 * 默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午7:21
 * {@code @version:} 1.0.0
 */

public class SimpleFilteredBlockEventMulticaster implements FilteredBlockEventMulticaster {

    LinkedHashSet<FilteredBlockEventListener<?>> listeners = new LinkedHashSet<>();

    @Override
    public void addListener(FilteredBlockEventListener<?> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(FilteredBlockEventListener<?> listener) {
        listeners.remove(listener);
    }

    @Override
    public void clearListener() {
        listeners.clear();
    }

    @Override
    public void multicastEvent(FilteredBlockEvent event) {
        listeners.forEach(listener -> invokeListener(listener, event));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void invokeListener(FilteredBlockEventListener listener, FilteredBlockEvent event) {
        listener.onEvent(event);
    }

}
