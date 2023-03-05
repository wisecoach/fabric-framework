package com.wisecoach.gatewayplus.spring.mapper;

import com.wisecoach.gatewayplus.proxy.MapperRegistry;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午3:41
 * {@code @version:} 1.0.0
 */

public class ClassPathMapperScanner extends ClassPathBeanDefinitionScanner {

    private MapperRegistry mapperRegistry;

    public ClassPathMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public void setMapperRegistry(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        processBeanDefinisions(beanDefinitionHolders);
        return beanDefinitionHolders;
    }

    private void processBeanDefinisions(Set<BeanDefinitionHolder> definitions) {
        AbstractBeanDefinition definition;
        BeanDefinitionRegistry registry = getRegistry();
        for (BeanDefinitionHolder holder : definitions) {
            definition = (AbstractBeanDefinition) holder.getBeanDefinition();
            String beanClassName = definition.getBeanClassName();

            // 设置好MapperFactoryBean的registry和要代理的mapper接口类
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            // 我们实际上生效的是MapperFactoryBean，而不是Mapper接口类
            definition.setBeanClass(MapperFactoryBean.class);
            definition.getPropertyValues().addPropertyValue("registry", new RuntimeBeanReference(MapperRegistry.class));

            registry.registerBeanDefinition(beanClassName, definition);
        }
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
