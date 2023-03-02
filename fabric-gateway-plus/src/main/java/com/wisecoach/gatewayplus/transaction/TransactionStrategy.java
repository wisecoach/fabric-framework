package com.wisecoach.gatewayplus.transaction;

/**
 * 该框架支持的事务策略
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午6:57
 * {@code @version:} 1.0.0
 */

public enum TransactionStrategy {
    /**
     * 直接异步提交策略，采用该策略的情况下，会在交易完成背书后成功直接异步提交给Orderer节点，
     * 后续会监听区块信息，如果事务上链失败，会写入日志，等待人工处理；
     */
    SUBMIT_ASYNC,

    /**
     * 同步提交策略，采用该策略的情况下，会在背书并提交给Orderer节点后，阻塞等待交易上链
     */
    SUBMIT_SYNC,

    /**
     * 事务提交策略，采用该策略的情况下，会在一个事务内（被注解的Service方法被调用期间），将所有的交易在背书后延迟到整个业务最后再进行提交；
     * 如果期间发生异常则不会向Orderer节点提交背书完的交易
     * 建议：配合Spring-TX的@Transactional使用，可以完成mysql和fabric交易的同时提交和回滚；
     * Note：在Fabric中，提交背书完的交易不代表上链成功，可能会发生世界状态的key在一个区块内被写入两次，会导致双花问题，上链就会失败；
     * 如果期间出现了双花问题而导致上链失败，上链失败的交易就会被写入到日志中，等待人工处理
     */
    TX_SUBMIT,

    /**
     * 带锁的事务提交策略，采用该策略的情况下，会在一个事务内，会将所有的交易在背书后延迟到整个业务最后再进行提交；此外，还会对背书结果的写集中
     * 的每个key进行上锁，不允许其他线程执行相关的交易，在中心化调用链码的情况下，可以避免双花问题的发生；并且会监听区块，如果交易成功上链就会
     * 对相应的key解锁
     * 此外为了避免意外情况（出现了非中心化的链码调用）的发生，也会将上链失败的交易写入日志，等待人工处理
     * Note：若采用该策略的情况下，要求所有 {@link com.wisecoach.gatewayplus.annotation.ChaincodeTransaction} 都采用该策略，否则
     * 会导致该策略失效
     */
    LOCKED_TX_SUBMIT

}
