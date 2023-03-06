package com.wisecoach.gatewayplus.spring.boot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午7:07
 * {@code @version:} 1.0.0
 */

@ConfigurationProperties(FabricGatewayPlusProperties.prefix)
public class FabricGatewayPlusProperties {

    public static final String prefix = "fabric.gatewayplus";

    /**
     * contractMapper默认的channelName
     */
    private String defaultChannel = "mychannel";

    public String getDefaultChannel() {
        return defaultChannel;
    }

    public void setDefaultChannel(String defaultChannel) {
        this.defaultChannel = defaultChannel;
    }

}
