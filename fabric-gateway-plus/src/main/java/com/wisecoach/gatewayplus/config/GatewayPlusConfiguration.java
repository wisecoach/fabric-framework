package com.wisecoach.gatewayplus.config;

import com.wisecoach.gatewayplus.bind.ContractCommandFactory;
import com.wisecoach.gatewayplus.bind.ContractResolver;
import com.wisecoach.gatewayplus.proxy.MapperRegistry;
import com.wisecoach.gatewayplus.transaction.ContractExecutor;
import com.wisecoach.gatewayplus.transaction.TransactionAdvice;
import com.wisecoach.util.Assert;

/**
 * 该框架的配置类，持有大量单例的依赖，主要是为了给proxy子模块进行解耦；感觉这个类的设计还是比较丑的，容易乱用，建议是在自动配置中不要把这个类注册
 * 进去，在创建{@link MapperRegistry}Bean的时候，同时初始化这个对象就行
 * 配置里面的对象都是必须的，在取得时保证不为空
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午1:36
 * {@code @version:} 1.0.0
 */


public class GatewayPlusConfiguration {
    /**
     * mapper注册器
     */
    private MapperRegistry mapperRegistry;

    /**
     * 合约执行器
     */
    private ContractExecutor contractExecutor;

    /**
     * 事务通知
     */
    private TransactionAdvice transactionAdvice;

    /**
     * 合约命令工厂
     */
    private ContractCommandFactory contractCommandFactory;

    /**
     * 合约解析器
     */
    private ContractResolver contractResolver;

    public GatewayPlusConfiguration(ContractExecutor executor, TransactionAdvice transactionAdvice, ContractCommandFactory contractCommandFactory, ContractResolver contractResolver) {
        this.contractExecutor = executor;
        this.transactionAdvice = transactionAdvice;
        this.contractCommandFactory = contractCommandFactory;
        this.contractResolver = contractResolver;
    }

    public MapperRegistry getMapperRegistry() {
        Assert.notNull(mapperRegistry, "请注入MapperRegistry");
        return mapperRegistry;
    }

    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    public ContractExecutor getContractExecutor() {
        Assert.notNull(contractExecutor, "请注入ContractExecutor");
        return contractExecutor;
    }

    public void setExecutor(ContractExecutor executor) {
        this.contractExecutor = executor;
    }

    public ContractCommandFactory getContractCommandFactory() {
        return contractCommandFactory;
    }

    public void setContractCommandFactory(ContractCommandFactory contractCommandFactory) {
        this.contractCommandFactory = contractCommandFactory;
    }

    public ContractResolver getContractResolver() {
        return contractResolver;
    }

    public void setContractResolver(ContractResolver contractResolver) {
        this.contractResolver = contractResolver;
    }

    public TransactionAdvice getTransactionAdvice() {
        return transactionAdvice;
    }

    public void setTransactionAdvice(TransactionAdvice transactionAdvice) {
        this.transactionAdvice = transactionAdvice;
    }

}
