package com.wisecoach.gatewayplus.spring.boot.configuration;

import com.wisecoach.gatewayplus.spring.mapper.ContractMapperScannerConfigurer;
import com.wisecoach.gatewayplus.spring.mapper.DisabledMapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/6/11 下午2:41
 * {@code @version:} 1.0.0
 */

@Configuration
@Import(DisabledGatewayPlusAutoConfiguration.AutoConfiguredMapperScannerRegistrar.class)
public class DisabledGatewayPlusAutoConfiguration {


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
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DisabledMapperScannerConfigurer.class);
            builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(packages));
            registry.registerBeanDefinition(ContractMapperScannerConfigurer.class.getName(), builder.getBeanDefinition());
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }
    }
}
