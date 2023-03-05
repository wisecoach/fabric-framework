package com.wisecoach.gatewayplus.spring.autoproxy;

import com.wisecoach.gatewayplus.proxy.ChaincodeTransactionInterceptor;
import com.wisecoach.gatewayplus.spring.annotation.ChaincodeService;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午3:49
 * {@code @version:} 1.0.0
 */


public class AnnotatedChaincodeServiceProxyAutoCreator extends AbstractChaincodeServiceProxyAutoCreator {

    public AnnotatedChaincodeServiceProxyAutoCreator(ChaincodeTransactionInterceptor interceptor) {
        super(interceptor);
    }

    public AnnotatedChaincodeServiceProxyAutoCreator(ChaincodeTransactionInterceptor interceptor, int processor_priority) {
        super(interceptor, processor_priority);
    }

    @Override
    protected boolean isNecessary(Object bean) {
        return bean.getClass().getAnnotation(ChaincodeService.class) != null;
    }
}
