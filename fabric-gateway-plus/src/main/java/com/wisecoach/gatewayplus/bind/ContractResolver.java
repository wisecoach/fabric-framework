package com.wisecoach.gatewayplus.bind;

import java.lang.reflect.Method;

/**
 * Contract方法的解析器，用于解析链码的参数和返回的结果
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午9:31
 * {@code @version:} 1.0.0
 */

public interface ContractResolver {
    /**
     * 解析参数为String
     * @param args 任意类型的参数
     * @return 转化为string的参数
     */
    String[] resolveArgs(Object[] args);

    /**
     * 解析链码的执行结果，转化为ContractMapper对应方法的具体类型
     * @param result 链码的执行结果
     * @return 解析后的返回值
     */
    Object resolveResult(byte[] result, Method method);
}
