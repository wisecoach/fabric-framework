package com.wisecoach.security.spring.boot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/25 下午1:47
 * {@code @version:} 1.0.0
 */

@ConfigurationProperties(prefix = FabricSecurityProperties.prefix)
public class FabricSecurityProperties {
    public final static String prefix = "fabric.security";
}
