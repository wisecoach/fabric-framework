package com.wisecoach.gatewayplus.event;

import org.hyperledger.fabric.protos.peer.FilteredBlock;

import java.util.EventObject;

/**
 * 取得{@link FilteredBlock}的事件
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午6:55
 * {@code @version:} 1.0.0
 */

public class FilteredBlockEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param block The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public FilteredBlockEvent(FilteredBlock block) {
        super(block);
    }

    public FilteredBlock getBlock() {
        return (FilteredBlock) getSource();
    }
}
