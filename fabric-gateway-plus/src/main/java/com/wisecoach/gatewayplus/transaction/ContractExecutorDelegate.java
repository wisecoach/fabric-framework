package com.wisecoach.gatewayplus.transaction;

import com.wisecoach.util.Assert;
import org.hyperledger.fabric.client.Contract;

import java.util.HashMap;
import java.util.Map;

/**
 * ContractExecutor的委派器，将要执行的合约转发给不同事务策略的ContractExecutor
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 上午10:01
 * {@code @version:} 1.0.0
 */

public class ContractExecutorDelegate implements ContractExecutor {

    private final Map<TransactionStrategy, ContractExecutor> executors = new HashMap<>();

    public ContractExecutorDelegate() {
        init();
    }

    // {@code TODO 都会把所有实现类写好了再弄这个}
    private void init() {

    }

    @Override
    public byte[] evaluate(Contract contract, String transactionName, String[] args) {
        return getExecutor().evaluate(contract, transactionName, args);
    }

    @Override
    public byte[] submit(Contract contract, String transactionName, String[] args) {
        return getExecutor().submit(contract, transactionName, args);
    }

    /**
     * 从 {@link TransactionContextHolder} 中取得交易上下文，并且从交易上下文中取得当前交易使用的策略
     * 然后根据策略取得对应的合约执行器
     * @return executor
     */
    private ContractExecutor getExecutor() {
        TransactionContext context = TransactionContextHolder.getContext();
        Assert.notNull(context, "请确保在执行合约之前，将TransactionContext注入到TransactionContextHolder中");
        TransactionStrategy strategy = context.getStrategy();
        ContractExecutor executor = executors.get(strategy);
        if (executor == null) {
            throw new TransactionException("不支持的策略：" + strategy + "\n因为没有该策略的合约执行器");
        }
        return executor;
    }

    /**
     * 注册一个策略和执行器的映射
     */
    private void register(TransactionStrategy strategy, ContractExecutor executor) {
        Assert.notNull(executor, "executor不可为空");
        executors.put(strategy, executor);
    }
}
