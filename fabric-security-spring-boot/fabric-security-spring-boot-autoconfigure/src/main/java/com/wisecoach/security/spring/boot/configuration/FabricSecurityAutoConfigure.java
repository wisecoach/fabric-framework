package com.wisecoach.security.spring.boot.configuration;

import com.wisecoach.security.interceptor.FabricTransactionInterceptor;
import com.wisecoach.security.spring.autoproxy.AnnotatedFabricServiceProxyAutoCreator;
import com.wisecoach.security.user.ArrayUserProviderManager;
import com.wisecoach.security.user.UserProviderManager;
import com.wisecoach.security.userinfo.NoOpUserInfoProvider;
import com.wisecoach.security.userinfo.UserInfoProvider;
import com.wisecoach.util.Assert;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
     * 默认使用 {@link ArrayUserProviderManager} 作为UserProviderManager
     */
    @ConditionalOnMissingClass
    @Bean
    public UserProviderManager userProviderManager() {
        return new ArrayUserProviderManager();
    }

    /**
     * 默认使用
     */
    @ConditionalOnMissingBean
    @Bean
    public AnnotatedFabricServiceProxyAutoCreator fabricServiceProxyAutoCreator(
            UserProviderManager userProviderManager,
            UserInfoProvider userInfoProvider
    ) {
        FabricTransactionInterceptor interceptor = new FabricTransactionInterceptor(userProviderManager, userInfoProvider);
        return new AnnotatedFabricServiceProxyAutoCreator(interceptor);
    }

}
