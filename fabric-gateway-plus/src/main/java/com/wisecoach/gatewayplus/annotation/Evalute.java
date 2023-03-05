package com.wisecoach.gatewayplus.annotation;

import java.lang.annotation.*;

/**
 * 用于标注一个ContractMapper的一个方法为一次Evaluate，表示只会找一个背书节点执行得到结果，而不会上链
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午7:37
 * {@code @version:} 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Evalute {
    /**
     * 链码中的交易名
     * @return transactionName
     */
    String value();
}
