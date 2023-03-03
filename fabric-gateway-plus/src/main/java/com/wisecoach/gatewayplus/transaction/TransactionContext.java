package com.wisecoach.gatewayplus.transaction;

/**
 * 事务上下文，详细内容还得看子类，不同的策略会采用不同的Context
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午9:45
 * {@code @version:} 1.0.0
 */

public interface TransactionContext {
    /**
     * @return 取得事务上下文的id
     */
    Long getId();

    /**
     * @return 取得当前上下文采用的事务策略
     */
    TransactionStrategy getStrategy();
}
