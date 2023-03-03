package com.wisecoach.gatewayplus.transaction;

import org.hyperledger.fabric.client.Contract;

/**
 * {@link Contract}实际上的执行器，通过对evaluate和submit的不同实现方式，用于实现不同的事务策略
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/3 下午8:21
 * {@code @version:} 1.0.0
 */

public interface ContractExecutor {
    /**
     * evaluate方式调用链码，只是找一个背书取得执行结果
     * @param contract 要执行的链码对象
     * @param transactionName 要执行的链码交易名
     * @param args 链码交易参数
     * @return 链码执行结果
     */
    byte[] evaluate(Contract contract, String transactionName, String[] args);

    /**
     * submit方式调用链码，完成背书并将交易提交给排序服务
     * @param contract 要执行的链码对象
     * @param transactionName 要执行的链码交易名
     * @param args 链码交易参数
     * @return 链码执行结果
     */
    byte[] submit(Contract contract, String transactionName, String[] args);
}
