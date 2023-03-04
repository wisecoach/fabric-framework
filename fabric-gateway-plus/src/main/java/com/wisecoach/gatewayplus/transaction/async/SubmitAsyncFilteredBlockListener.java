package com.wisecoach.gatewayplus.transaction.async;

import com.wisecoach.gatewayplus.event.FilteredBlockEvent;
import com.wisecoach.gatewayplus.event.FilteredBlockEventListener;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午7:27
 * {@code @version:} 1.0.0
 */

public interface SubmitAsyncFilteredBlockListener extends FilteredBlockEventListener<FilteredBlockEvent> {
    void subscribe(String txid);
}
