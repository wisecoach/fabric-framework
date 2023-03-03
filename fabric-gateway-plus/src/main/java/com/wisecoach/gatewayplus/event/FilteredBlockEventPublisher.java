package com.wisecoach.gatewayplus.event;

/**
 * {@link FilteredBlockEvent} 的时间发布器
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午7:01
 * {@code @version:} 1.0.0
 */

public interface FilteredBlockEventPublisher {

    void publish(FilteredBlockEvent event);

}
