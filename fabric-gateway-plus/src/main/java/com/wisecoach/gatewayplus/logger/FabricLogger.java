package com.wisecoach.gatewayplus.logger;

import jdk.nashorn.internal.ir.Block;
import org.hyperledger.fabric.protos.peer.FilteredBlock;
import org.hyperledger.fabric.protos.peer.FilteredTransaction;
import org.hyperledger.fabric.protos.peer.Transaction;

/**
 * Fabric的日志类
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午6:43
 * {@code @version:} 1.0.0
 */

public interface FabricLogger {

    /**
     * 记录合法过滤交易
     */
    default void logValidFilteredTransaction(FilteredTransaction transaction) {}

    /**
     * 记录不合法过滤交易
     */
    default void logInvalidFilteredTransaction(FilteredTransaction transaction) {}

    /**
     * 记录合法交易
     */
    default void logValidTransaction(Transaction transaction) {}

    /**
     * 记录不合法交易
     */
    default void logInvalidTransaction(Transaction transaction) {}

    /**
     * 记录区块
     */
    default void logBlock(Block block) {}

    /**
     * 记录过滤区块
     */
    default void logFilteredBlock(FilteredBlock block) {}

}
