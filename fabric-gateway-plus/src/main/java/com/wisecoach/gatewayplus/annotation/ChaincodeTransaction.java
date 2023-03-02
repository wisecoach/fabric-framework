package com.wisecoach.gatewayplus.annotation;

import com.wisecoach.gatewayplus.transaction.TransactionStrategy;

import java.lang.annotation.*;

/**
 * 表示该方法需要调用Chaincode，也会指示出这个Service会执行怎样的事务策略
 *
 * @see
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午6:51
 * {@code @version:} 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ChaincodeTransaction {
    /**
     * 该Service方法采用的事务等级，默认为直接异步提交
     * Note：请确保只要一个方法采用了 {@link TransactionStrategy#LOCKED_TX_SUBMIT}，
     * 那么所有方法都采用 {@link TransactionStrategy#LOCKED_TX_SUBMIT}
     * @return strategy
     */
    TransactionStrategy value() default TransactionStrategy.SUBMIT_ASYNC;
}
