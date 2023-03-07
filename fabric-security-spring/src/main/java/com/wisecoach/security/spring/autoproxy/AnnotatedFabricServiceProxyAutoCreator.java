package com.wisecoach.security.spring.autoproxy;

import com.wisecoach.security.interceptor.FabricTransactionInterceptor;
import com.wisecoach.security.spring.annotation.FabricService;
import org.springframework.core.annotation.AnnotationUtils;

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
        // 可能这个对象被动态代理过，同时得去找其父类和接口的注解
        FabricService annotation = AnnotationUtils.findAnnotation(bean.getClass(), FabricService.class);
        return annotation != null;
    }
}
