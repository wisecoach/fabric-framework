package com.wisecoach.gatewayplus.transaction.async;

import com.wisecoach.gatewayplus.transaction.ContractExecutor;
import com.wisecoach.gatewayplus.transaction.TransactionStrategy;
import org.hyperledger.fabric.client.*;

/**
 * 为了实现 {@link TransactionStrategy#SUBMIT_ASYNC}策略的合同执行器
 * 在创建Proposal后，会完成背书并异步提交给排序节点，并将txid让 {@link SubmitAsyncFilteredBlockListener}订阅
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午7:15
 * {@code @version:} 1.0.0
 */


public class SubmitAsyncContractExecutor implements ContractExecutor {

    private final SubmitAsyncFilteredBlockListener listener;

    public SubmitAsyncContractExecutor(SubmitAsyncFilteredBlockListener listener) {
        this.listener = listener;
    }

    /**
     * evaluate只需要正常地调用evaluate即可
     * @param contract 要执行的链码对象
     * @param transactionName 要执行的链码交易名
     * @param args 链码交易参数
     */
    @Override
    public byte[] evaluate(Contract contract, String transactionName, String[] args) {
        try {
             return contract.evaluateTransaction(transactionName, args);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 直接背书异步提交并订阅
     * @param contract 要执行的链码对象
     * @param transactionName 要执行的链码交易名
     * @param args 链码交易参数
     * @return 链码执行结果
     */
    @Override
    public byte[] submit(Contract contract, String transactionName, String[] args) {
        try {
            Transaction transaction = contract.newProposal(transactionName).addArguments(args).build().endorse();
            SubmittedTransaction submitAsync = transaction.submitAsync();

            // 成功背书提交给背书节点后，将txid交给listener
            listener.subscribe(transaction.getTransactionId());
            return submitAsync.getResult();
        } catch (EndorseException | SubmitException e) {
            throw new RuntimeException(e);
        }
    }
}
