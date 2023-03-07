package com.wisecoach.gatewayplus.transaction;

/**
 * 用于描述一个类方法的事务属性
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 上午10:59
 * {@code @version:} 1.0.0
 */

public interface TransactionAttribute {
    /**
     * 取得事务的策略
     */
    TransactionStrategy getTransactionStrategy();

    /**
     * 取得事务名，默认为 {@code qualified class name + "." + method name}
     */
    String getName();
}
