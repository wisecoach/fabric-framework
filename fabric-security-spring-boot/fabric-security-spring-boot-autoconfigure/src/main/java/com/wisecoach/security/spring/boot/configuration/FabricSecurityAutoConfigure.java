package com.wisecoach.security.spring.boot.configuration;

import com.wisecoach.security.spring.interceptor.FabricTransactionInterceptor;
import com.wisecoach.security.spring.interceptor.UserAttributeSourceAdvisor;
import com.wisecoach.security.user.*;
import com.wisecoach.security.userinfo.NoOpUserInfoProvider;
import com.wisecoach.security.userinfo.UserInfoProvider;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;

/**
 * Fabric-security的自动配置类
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/25 下午1:47
 * {@code @version:} 1.0.0
 */

@Configuration
@EnableConfigurationProperties(FabricSecurityProperties.class)
public class FabricSecurityAutoConfigure {

    private final FabricSecurityProperties properties;

    public FabricSecurityAutoConfigure(FabricSecurityProperties properties) {
        this.properties = properties;
    }

    /**
     * 如果用户没有注册自定义 {@link UserInfoProvider} ，则使用 {@link NoOpUserInfoProvider}
     */
    @Bean
    @ConditionalOnMissingBean(UserInfoProvider.class)
    public UserInfoProvider userInfoProvider() {
        return new NoOpUserInfoProvider();
    }

    /**
     * 要找出所有注册在容器中的UserProvider并注册到Manager中
     */
    @Configuration
    public static class AutoConfigureProviderManager implements ApplicationContextAware {
        private ApplicationContext ctx;

        /**
         * 默认使用 {@link ArrayUserProviderManager} 作为UserProviderManager
         */
        @ConditionalOnMissingClass
        @Bean
        public UserProviderManager userProviderManager() {
            ArrayUserProviderManager manager = new ArrayUserProviderManager();
            Arrays.stream(ctx.getBeanNamesForType(UserProvider.class)).forEach(name -> manager.register(ctx.getBean(name, UserProvider.class)));
            return manager;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.ctx = applicationContext;
        }
    }


    @Bean
    public UserAttributeSource userAttributeSource() {
        return new AnnotationUserAttributeSource();
    }

    @Bean
    public FabricTransactionInterceptor chaincodeUserInterceptor(
            UserAttributeSource source,
            UserProviderManager manager,
            UserInfoProvider provider
    ) {
        return new FabricTransactionInterceptor(source, manager, provider);
    }

    @Bean
    public UserAttributeSourceAdvisor userAttributeSourceAdvisor(
            FabricTransactionInterceptor fabricTransactionInterceptor) {
        return new UserAttributeSourceAdvisor(fabricTransactionInterceptor);
    }
}
