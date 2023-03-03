package com.wisecoach.gatewayplus.event;

import java.util.EventListener;

/**
 * {@link FilteredBlockEvent} 的监听器
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午7:06
 * {@code @version:} 1.0.0
 */

public interface FilteredBlockEventListener<E extends FilteredBlockEvent> extends EventListener {
    void onEvent(E event);
}
