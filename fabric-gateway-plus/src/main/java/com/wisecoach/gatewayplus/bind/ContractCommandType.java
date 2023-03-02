package com.wisecoach.gatewayplus.bind;

/**
 * 链码执行类型
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午9:19
 * {@code @version:} 1.0.0
 */

public enum ContractCommandType {
    /**
     * 执行链码只是找一个背书节点背书取得执行结果
     */
    EVALUATE,

    /**
     * 执行链码会去找足够多的背书节点背书并打包成交易后提交给Orderer节点排序上链
     */
    SUBMIT
}
