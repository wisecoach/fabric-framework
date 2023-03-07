package com.wisecoach.config;

import com.wisecoach.fabric.security.CustomUserInfoProvider;
import com.wisecoach.fabric.security.CustomUserProvider;
import com.wisecoach.fabric.security.CustomUserProvider2;
import com.wisecoach.security.user.UserProviderManager;
import com.wisecoach.security.userinfo.UserInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CustomUserProvider customUserProvider() {
        return new CustomUserProvider();
    }

    @Bean
    public CustomUserProvider2 customUserProvider2() {
        return new CustomUserProvider2();
    }
}
