package com.wisecoach.gatewayplus.event;

import java.util.EventListener;

/**
 * {@link MapperEvent} 的监听器
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午7:03
 * {@code @version:} 1.0.0
 */

@FunctionalInterface
public interface MapperEventListener<E extends MapperEvent> extends EventListener {
    /**
     * 处理监听到的事件
     * @param event 要处理的事件
     */
    void onEvent(E event);
}
