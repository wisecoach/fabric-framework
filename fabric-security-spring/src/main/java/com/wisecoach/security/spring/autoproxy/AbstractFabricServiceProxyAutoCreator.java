package com.wisecoach.security.spring.autoproxy;

import com.wisecoach.annotation.Nullable;
import com.wisecoach.security.interceptor.FabricTransactionInterceptor;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * FabricService的自动代理对象创建器
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午5:26
 * {@code @version:} 1.0.0
 */


public abstract class AbstractFabricServiceProxyAutoCreator implements BeanPostProcessor {

    private final FabricTransactionInterceptor interceptor;

    public AbstractFabricServiceProxyAutoCreator(FabricTransactionInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return wrapIfNecessary(bean);
    }

    protected Object wrapIfNecessary(Object bean) {
        if (isNecessary(bean)) {
            return createProxy(bean);
        }
        return bean;
    }

    protected Object createProxy(Object bean) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(interceptor);
        return enhancer.create();
    }

    protected abstract boolean isNecessary(Object bean);
}
