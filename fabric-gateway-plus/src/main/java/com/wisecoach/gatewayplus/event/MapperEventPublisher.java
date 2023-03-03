package com.wisecoach.gatewayplus.event;

/**
 * 发出Mapper事件的事件源接口
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午6:48
 * {@code @version:} 1.0.0
 */

public interface MapperEventPublisher {
    /**
     * 发布一个mapper时间给符合的listener
     * @param event mapper事件
     */
    void publish(MapperEvent event);
}
