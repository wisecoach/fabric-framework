package com.wisecoach.gatewayplus.transaction.async;

import org.hyperledger.fabric.protos.peer.FilteredBlock;

/**
 * 上链失败无所谓，桥洞底下盖小被，直接开摆
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午7:10
 * {@code @version:} 1.0.0
 */


public class NoopSubmitAsyncFilteredBlockListener extends AbstractSubmitAsyncFilteredBlockListener {

    @Override
    protected void doHandleBlock(FilteredBlock block) {

    }

    @Override
    protected void doSubscribe(String txid) {

    }
}
