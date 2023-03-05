package com.wisecoach.gatewayplus.spring.autoproxy;

import com.wisecoach.annotation.Nullable;
import com.wisecoach.gatewayplus.proxy.ChaincodeTransactionInterceptor;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * ChaincodeService的自动代理对象创建器
 * 这里实现了 {@link Ordered} 接口，主要是为了控制BeanPostProcessor的执行顺序，可能会有需要先调用该加强
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午3:44
 * {@code @version:} 1.0.0
 */

public abstract class AbstractChaincodeServiceProxyAutoCreator implements BeanPostProcessor, Ordered {

    private final static int DEFAULT_PRIORITY = 10;

    private final ChaincodeTransactionInterceptor interceptor;
    private final int processor_priority;

    public AbstractChaincodeServiceProxyAutoCreator(ChaincodeTransactionInterceptor interceptor) {
        this.interceptor = interceptor;
        this.processor_priority = DEFAULT_PRIORITY;
    }

    public AbstractChaincodeServiceProxyAutoCreator(ChaincodeTransactionInterceptor interceptor, int processor_priority) {
        this.interceptor = interceptor;
        this.processor_priority = processor_priority;
    }

    @Override
    public int getOrder() {
        return processor_priority;
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
