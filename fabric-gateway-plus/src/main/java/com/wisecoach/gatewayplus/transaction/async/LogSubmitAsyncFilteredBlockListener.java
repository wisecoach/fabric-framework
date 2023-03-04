package com.wisecoach.gatewayplus.transaction.async;

import com.wisecoach.gatewayplus.logger.FabricLogger;
import org.hyperledger.fabric.protos.peer.FilteredBlock;
import org.hyperledger.fabric.protos.peer.TxValidationCode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 上链失败监听器，可以主动监听某些txid的交易是否成功上链，上链失败则会写入日志
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午7:05
 * {@code @version:} 1.0.0
 */


public class LogSubmitAsyncFilteredBlockListener extends AbstractSubmitAsyncFilteredBlockListener {
    /**
     * 占位符，HashSet就是HashMap存这种占位符实现的
     */
    private final static Object PRESENT = new Object();

    /**
     * 上链失败交易的日志
     */
    private final FabricLogger logger;
    /**
     * 记录关注的交易id
     * Note：这是线程安全的
     */
    private final Map<String, Object> txs = new ConcurrentHashMap<>();

    public LogSubmitAsyncFilteredBlockListener(FabricLogger logger) {
        this.logger = logger;
    }

    @Override
    protected void doHandleBlock(FilteredBlock block) {
        // 找出这个区块中 txs包含的交易，如果其中的交易是不合法的，那么就写入到日志中
        block.getFilteredTransactionsList().stream().filter(tx -> txs.containsKey(tx.getTxid())).forEach(tx -> {
            txs.remove(tx.getTxid());
            if (tx.getTxValidationCode() != TxValidationCode.VALID) {
                logger.logInvalidFilteredTransaction(tx);
            }
        });
    }

    @Override
    protected void doSubscribe(String txid) {
        txs.put(txid, PRESENT);
    }
}
