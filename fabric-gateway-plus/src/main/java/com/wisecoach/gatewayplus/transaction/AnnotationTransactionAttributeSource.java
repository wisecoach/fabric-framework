package com.wisecoach.gatewayplus.transaction;

import com.wisecoach.gatewayplus.annotation.ChaincodeTransaction;
import com.wisecoach.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 注解的实现类，我们直接在这完成对注解的读取，并且生成TransactionAttribute，不考虑再做一层注解的解析层的抽象
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 上午11:39
 * {@code @version:} 1.0.0
 */


public class AnnotationTransactionAttributeSource extends AbstractTransactionAttributeSource {

    /**
     * 都是有资格的
     */
    @Override
    public boolean isCandidateClass(Class<?> targetClass) {
        return true;
    }

    @Override
    protected TransactionAttribute findTransactionAttribute(Method method) {
        ChaincodeTransaction chaincodeTransaction = method.getAnnotation(ChaincodeTransaction.class);
        if (chaincodeTransaction == null) {
            return null;
        }
        String name = chaincodeTransaction.name();
        if (!StringUtils.hasLength(name)) {
            name = method.getDeclaringClass().getSimpleName() + ":" + method.getName();
        }
        TransactionStrategy strategy = chaincodeTransaction.value();
        return new DefaultTransactionAttribute(strategy, name);
    }
}
