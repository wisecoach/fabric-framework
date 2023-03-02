package com.wisecoach.gatewayplus.annotation;

/**
 * 用于标注一个ContractMapper的一个方法为一次Submit，表示该方法会去完成背书，并且发送给排序节点进行上链
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午7:38
 * {@code @version:} 1.0.0
 */

public @interface Submit {

    /**
     * 链码中的transactionName
     * @return transactionName
     */
    String value();
}
