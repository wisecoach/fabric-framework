package com.wisecoach.security.spring.autoproxy;

import com.wisecoach.security.interceptor.FabricTransactionInterceptor;
import com.wisecoach.security.spring.annotation.FabricService;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午5:31
 * {@code @version:} 1.0.0
 */


public class AnnotatedFabricServiceProxyAutoCreator extends AbstractFabricServiceProxyAutoCreator {

    public AnnotatedFabricServiceProxyAutoCreator(FabricTransactionInterceptor interceptor) {
        super(interceptor);
    }

    @Override
    protected boolean isNecessary(Object bean) {
        FabricService annotation = bean.getClass().getAnnotation(FabricService.class);
        return annotation != null;
    }
}
