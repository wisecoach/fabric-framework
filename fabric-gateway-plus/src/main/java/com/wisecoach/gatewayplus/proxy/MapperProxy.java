package com.wisecoach.gatewayplus.proxy;

import com.wisecoach.gatewayplus.bind.ContractCommand;
import com.wisecoach.gatewayplus.bind.ContractCommandFactory;
import com.wisecoach.gatewayplus.bind.ContractResolver;
import com.wisecoach.gatewayplus.config.GatewayPlusConfiguration;
import com.wisecoach.gatewayplus.transaction.ContractExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * ContractMapper代理类，也就是实际上的实现类，它将采用JDK原生动态代理实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 上午11:18
 * {@code @version:} 1.0.0
 */


public class MapperProxy<T> implements InvocationHandler {

    /**
     * ContractMapper的接口类
     */
    private final Class<T> mapperInterface;
    /**
     * ContractMapper的方法和加强方法执行器的映射
     */
    private final Map<Method, MapperMethodInvoker> methodCache;
    /**
     * 配置类
     */
    private final GatewayPlusConfiguration configuration;

    public MapperProxy(Class<T> mapperInterface, Map<Method, MapperMethodInvoker> methodCache, GatewayPlusConfiguration configuration) {
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
        this.configuration = configuration;
    }

    /**
     * 强化方法就是除了Object自带的方法外，都调用对应方法的加强方法执行器
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(proxy, args);
            } else {
                return cachedInvoker(method).invoke(proxy, method, args);
            }
        }catch (Throwable e) {
            throw e;
        }
    }

    /**
     * 尝试取得method对应的MapperMethodInvoker，如果没有取得就创建
     */
    private MapperMethodInvoker cachedInvoker(Method method) {
        MapperMethodInvoker invoker = methodCache.get(method);
        if (invoker != null) {
            return invoker;
        }
        invoker = methodCache.computeIfAbsent(method, m -> {
            ContractCommandFactory commandFactory = configuration.getContractCommandFactory();
            ContractCommand command = commandFactory.getCommand(mapperInterface, method);
            ContractResolver resolver = configuration.getContractResolver();
            ContractExecutor executor = configuration.getContractExecutor();
            return new DefaultMapperMethodInvoker(new MapperMethod(command, executor, resolver));
        });
        return invoker;
    }

    interface MapperMethodInvoker {
        Object invoke(Object proxy, Method method, Object[] args) throws Exception;
    }

    static class DefaultMapperMethodInvoker implements MapperMethodInvoker {

        private final MapperMethod mapperMethod;

        public DefaultMapperMethodInvoker(MapperMethod mapperMethod) {
            this.mapperMethod = mapperMethod;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            return mapperMethod.execute(args);
        }
    }
}
