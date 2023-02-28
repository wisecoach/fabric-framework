package com.wisecoach.config;

import com.wisecoach.fabric.security.CustomUserInfoProvider;
import com.wisecoach.fabric.security.CustomUserProvider;
import com.wisecoach.security.user.UserProviderManager;
import com.wisecoach.security.userinfo.UserInfoProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/28 下午8:46
 * {@code @version:} 1.0.0
 */

@Configuration
public class FabricSecurityConfiguration {

    @Bean
    public UserInfoProvider userInfoProvider() {
        return new CustomUserInfoProvider();
    }

    @Bean
    public UserProviderManager addUserProvider(UserProviderManager manager){
        manager.register(new CustomUserProvider());
        return manager;
    }
}
