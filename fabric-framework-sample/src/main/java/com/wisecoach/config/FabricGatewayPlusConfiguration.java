package com.wisecoach.config;

import com.wisecoach.fabric.CustomGatewayInfoProvider;
import com.wisecoach.gatewayplus.info.GatewayInfoProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午1:05
 * {@code @version:} 1.0.0
 */

@Configuration
// @EnableFabricGatewayPlus
public class FabricGatewayPlusConfiguration {

    @Bean
    public GatewayInfoProvider gatewayInfoProvider() {
        return new CustomGatewayInfoProvider();
    }

}
