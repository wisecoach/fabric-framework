package com.wisecoach.gatewayplus.event;

/**
 * 将分发 {@link FilteredBlockEvent} 和 管理 {@link FilteredBlockEventListener}的业务逻辑从 {@link FilteredBlockEventPublisher} 移到该接口，
 * 令{@link FilteredBlockEventPublisher}更注重业务本身
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午7:18
 * {@code @version:} 1.0.0
 */

public interface FilteredBlockEventMulticaster {
    void addListener(String channel, FilteredBlockEventListener<?> listener);
    void removeListener(String channel, FilteredBlockEventListener<?> listener);
    void clearListener();
    void multicastEvent(FilteredBlockEvent event);
}
