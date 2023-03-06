package com.wisecoach.gatewayplus.spring.boot.configuration;

import com.wisecoach.gatewayplus.bind.AnnotatedContractCommandFactory;
import com.wisecoach.gatewayplus.bind.ContractCommandFactory;
import com.wisecoach.gatewayplus.bind.ContractResolver;
import com.wisecoach.gatewayplus.config.GatewayPlusConfiguration;
import com.wisecoach.gatewayplus.connection.GrpcConnSource;
import com.wisecoach.gatewayplus.connection.GrpcConnSourceDelegate;
import com.wisecoach.gatewayplus.connection.PooledGrpcConnSource;
import com.wisecoach.gatewayplus.event.*;
import com.wisecoach.gatewayplus.info.GatewayInfoProvider;
import com.wisecoach.gatewayplus.info.NoOpGatewayInfoProvider;
import com.wisecoach.gatewayplus.proxy.ChaincodeTransactionInterceptor;
import com.wisecoach.gatewayplus.proxy.MapperRegistry;
import com.wisecoach.gatewayplus.session.GatewaySessionProvider;
import com.wisecoach.gatewayplus.session.GatewaySessionProviderImpl;
import com.wisecoach.gatewayplus.spring.autoproxy.AnnotatedChaincodeServiceProxyAutoCreator;
import com.wisecoach.gatewayplus.spring.mapper.MapperScannerConfigurer;
import com.wisecoach.gatewayplus.transaction.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午7:06
 * {@code @version:} 1.0.0
 */

@Configuration
@EnableConfigurationProperties(FabricGatewayPlusProperties.class)
@Import(FabricGatewayPlusAutoConfigure.AutoConfiguredMapperScannerRegistrar.class)
public class FabricGatewayPlusAutoConfigure {

    private final FabricGatewayPlusProperties properties;

    public FabricGatewayPlusAutoConfigure(FabricGatewayPlusProperties properties) {
        this.properties = properties;
    }

    // ------------------- info -------------------

    @Bean
    @ConditionalOnMissingBean
    public GatewayInfoProvider gatewayInfoProvider() {
        return new NoOpGatewayInfoProvider();
    }

    // ------------------- bind ---------------------------

    @Bean
    @ConditionalOnMissingBean
    public ContractCommandFactory contractCommandFactory() {
        return new AnnotatedContractCommandFactory(properties.getDefaultChannel());
    }

    // TODO 现在还没有实现
    @Bean
    @ConditionalOnMissingBean
    public ContractResolver contractResolver() {
        return null;
    }


    // ------------------- event --------------------------

    // TODO 到时候要注册上监听器
    @Bean
    public MapperEventMulticaster mapperEventMulticaster() {
        return new SimpleMapperEventMulticaster();
    }

    @Bean
    public FilteredBlockEventMulticaster filteredBlockEventMulticaster() {
        return new SimpleFilteredBlockEventMulticaster();
    }

    // ------------------- session ------------------------

    @Bean
    public GatewaySessionProvider gatewaySessionProvider() {
        return new GatewaySessionProviderImpl();
    }

    // ------------------- transaction --------------------

    // TODO 之后这里肯定有一些实现类需要从容器中拿依赖，只能在这里注册了
    @Bean
    @Primary
    public ContractExecutor contractExecutor() {
        return new ContractExecutorDelegate();
    }

    // TODO 之后这里肯定有一些实现类需要从容器中拿依赖，只能在这里注册了
    @Bean
    @Primary
    public TransactionAdvice transactionAdvice() {
        return new TransactionAdviceDelegate();
    }

    @Bean
    public TransactionContextProvider transactionContextProvider() {
        return new DefaultTransactionContextProvider();
    }

    // ------------------- proxy --------------------------

    @Bean
    public AnnotatedChaincodeServiceProxyAutoCreator chaincodeServiceProxyAutoCreator(
            TransactionAdvice advice,
            TransactionContextProvider transactionContextProvider,
            GatewayInfoProvider gatewayInfoProvider,
            GatewaySessionProvider gatewaySessionProvider) {
        ChaincodeTransactionInterceptor interceptor = new ChaincodeTransactionInterceptor(advice, transactionContextProvider, gatewayInfoProvider, gatewaySessionProvider);
        return new AnnotatedChaincodeServiceProxyAutoCreator(interceptor);
    }

    @Bean
    public MapperRegistry mapperRegistry(
            ContractExecutor contractExecutor,
            TransactionAdvice transactionAdvice,
            ContractCommandFactory contractCommandFactory,
            ContractResolver contractResolver,
            MapperEventMulticaster multicaster
    ) {
        GatewayPlusConfiguration configuration = new GatewayPlusConfiguration(contractExecutor, transactionAdvice, contractCommandFactory, contractResolver);
        MapperRegistry registry = new MapperRegistry(configuration, multicaster);
        configuration.setMapperRegistry(registry);
        return registry;
    }

    // ------------------- spring-mapper ------------------

    /**
     * 简单讲一下这个类起作用的方式：
     * 1. Spring容器启动会有这么几个阶段
     *      1. 生成BeanFactory
     *      2. 后处理BeanFactory
     *      3. 生成(getBean)并调用BeanFactoryPostProcessor的后处理方法
     *          1. 优先处理 继承类 BeanDefinitionRegistryProcessor
     *              1. 这里也要按PriorOrdered, Ordered, 一般的顺序调用，其中ConfigurationClassPostProcessor属于PriorOrdered中最晚的
     *                  1. 它会处理所有Configuration注解的ConfigurationClass，处理它的@Bean, @ComponentScan, @Import, @Resource, 导入的 ImportBeanDefinitionRegistrar
     *                  2. 而AutoConfiguredMapperScannerRegistrar就会被ConfigurationClassPostProcessor处理注册MapperScannerConfigurer的BeanDefinition
     *              2. MapperScannerConfigurer是一般的BeanDefinitionRegistryProcessor，它将在此时开始注册我们的Mapper对象
     */
    public static class AutoConfiguredMapperScannerRegistrar implements BeanFactoryAware, ImportBeanDefinitionRegistrar {

        private BeanFactory beanFactory;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            if (!AutoConfigurationPackages.has(beanFactory)) {
                return;
            }
            // 取得BeanFactory所对应的包
            List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
            // 创建 MapperScannerConfigurer 的 BeanDefinition
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
            builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(packages));
            registry.registerBeanDefinition(MapperScannerConfigurer.class.getName(), builder.getBeanDefinition());
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }
    }

}
