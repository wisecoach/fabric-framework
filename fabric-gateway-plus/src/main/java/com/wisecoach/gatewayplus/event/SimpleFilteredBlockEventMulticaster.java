package com.wisecoach.gatewayplus.event;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 默认实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午7:21
 * {@code @version:} 1.0.0
 */

public class SimpleFilteredBlockEventMulticaster implements FilteredBlockEventMulticaster {

    Map<String, Set<FilteredBlockEventListener<?>>> channelToListeners = new HashMap<>();


    @Override
    public void addListener(String channel, FilteredBlockEventListener<?> listener) {
        Set<FilteredBlockEventListener<?>> listeners = channelToListeners.computeIfAbsent(channel, k -> new LinkedHashSet<>());
        listeners.add(listener);
    }

    @Override
    public void removeListener(String channel, FilteredBlockEventListener<?> listener) {
        Set<FilteredBlockEventListener<?>> listeners = channelToListeners.get(channel);
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

    @Override
    public void clearListener() {
        channelToListeners.clear();
    }

    @Override
    public void multicastEvent(FilteredBlockEvent event) {
        channelToListeners.get(event.getBlock().getChannelId()).forEach(listener -> invokeListener(listener, event));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void invokeListener(FilteredBlockEventListener listener, FilteredBlockEvent event) {
        listener.onEvent(event);
    }

}
