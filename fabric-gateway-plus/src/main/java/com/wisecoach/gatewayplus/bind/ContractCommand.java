package com.wisecoach.gatewayplus.bind;

/**
 * 用来描述一个链码方法的信息
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午9:13
 * {@code @version:} 1.0.0
 */

public interface ContractCommand {
    /**
     * @return ContractMapper方法的全名
     */
    String getName();

    /**
     * @return Channel名
     */
    String getChannelName();

    /**
     * @return 链码名
     */
    String getContractName();

    /**
     * @return 要调用链码中的TransactionName
     */
    String getTransactionName();

    /**
     * @return 取得链码执行类型
     */
    ContractCommandType getContractCommandType();
}
