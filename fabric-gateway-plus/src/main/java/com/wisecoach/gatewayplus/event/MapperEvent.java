package com.wisecoach.gatewayplus.event;

import java.util.EventObject;

/**
 * Mapper相关的事件，用于通知其他模块进行对应的操作
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午6:32
 * {@code @version:} 1.0.0
 */

public abstract class MapperEvent extends EventObject {

    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MapperEvent(Object source) {
        super(source);
        timestamp = System.currentTimeMillis();
    }

    public final long getTimestamp() {
        return timestamp;
    }
}
