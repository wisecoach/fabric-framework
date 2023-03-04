package com.wisecoach.gatewayplus.transaction.async;

import com.wisecoach.gatewayplus.event.FilteredBlockEvent;
import com.wisecoach.gatewayplus.event.FilteredBlockEventListener;
import com.wisecoach.gatewayplus.transaction.TransactionStrategy;
import org.hyperledger.fabric.protos.peer.FilteredBlock;

/**
 * 为了实现 {@link TransactionStrategy#SUBMIT_ASYNC}策略的区块监听器
 * 主要是为了监听订阅的交易是否成功上链
 * {@code TODO 也得考虑懒得管上链失败的情况}
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午6:35
 * {@code @version:} 1.0.0
 */


public abstract class AbstractSubmitAsyncFilteredBlockListener implements SubmitAsyncFilteredBlockListener {

    @Override
    public void onEvent(FilteredBlockEvent event) {
        FilteredBlock block = event.getBlock();
        doHandleBlock(block);
    }

    protected abstract void doHandleBlock(FilteredBlock block);

    /**
     * 表示要去订阅txid这个交易
     * @param txid 交易id
     */
    @Override
    public void subscribe(String txid) {
        doSubscribe(txid);
    }

    protected abstract void doSubscribe(String txid);

}
