package com.wisecoach.security.spring.autoproxy;

import com.wisecoach.annotation.Nullable;
import com.wisecoach.security.interceptor.FabricTransactionInterceptor;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * FabricService的自动代理对象创建器
 * 这里实现了 {@link Ordered} 接口，主要是为了控制BeanPostProcessor的执行顺序，可能会有需要后调用该加强
 * Note：
 * Deprecated: 目前这个框架注入到容器中的方式是在BeanPostProcessor的后初始化阶段，为bean对象进行CGLib动态代理，
 * 但是无论是CGLib还是JDK动态代理都是无法重复对一个类进行两次动态代理的，使用了这个方式将无法使用SpringAOP强化，
 * 后续将采用SpringAOP的技术将该框架注入
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午5:26
 * {@code @version:} 1.0.0
 */
@Deprecated
public abstract class AbstractFabricServiceProxyAutoCreator implements BeanPostProcessor, Ordered {

    private final static int DEFAULT_PRIORITY = 10;

    private final FabricTransactionInterceptor interceptor;
    private final int processor_priority;

    public AbstractFabricServiceProxyAutoCreator(FabricTransactionInterceptor interceptor) {
        this.interceptor = interceptor;
        this.processor_priority = DEFAULT_PRIORITY;
    }

    public AbstractFabricServiceProxyAutoCreator(FabricTransactionInterceptor interceptor, int processor_priority) {
        this.interceptor = interceptor;
        this.processor_priority = processor_priority;
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

    @Override
    public int getOrder() {
        return processor_priority;
    }

    protected abstract boolean isNecessary(Object bean);
}
