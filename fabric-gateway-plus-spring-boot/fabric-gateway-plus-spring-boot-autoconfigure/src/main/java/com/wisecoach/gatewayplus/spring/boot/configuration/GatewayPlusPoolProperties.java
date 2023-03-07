package com.wisecoach.gatewayplus.spring.boot.configuration;

import com.wisecoach.gatewayplus.connection.GrpcConnSource;
import com.wisecoach.gatewayplus.connection.GrpcConnSourceDelegate;
import com.wisecoach.gatewayplus.connection.PooledGrpcConnSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 上午11:59
 * {@code @version:} 1.0.0
 */

@ConfigurationProperties(GatewayPlusPoolProperties.prefix)
public class GatewayPlusPoolProperties {

    public static final String prefix = "fabric.gatewayplus.pool";

    /**
     * 是否采用池化技术
     */
    private Boolean usePool = true;

    /**
     * 池子最多维持对象数
     */
    private Integer maxTotal = -1;

    /**
     * 每个键的一个池子最多几个对象
     */
    private Integer maxTotalPerKey = 8;

    /**
     * 每个键至少要保持多少个空闲对象
     */
    private Integer minIdelPerKey = 0;

    /**
     * 每个键最多维持多少个空闲对象
     */
    private Integer maxIdelPerKey = 8;

    public Boolean getUsePool() {
        return usePool;
    }

    public void setUsePool(Boolean usePool) {
        this.usePool = usePool;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxTotalPerKey() {
        return maxTotalPerKey;
    }

    public void setMaxTotalPerKey(Integer maxTotalPerKey) {
        this.maxTotalPerKey = maxTotalPerKey;
    }

    public Integer getMinIdelPerKey() {
        return minIdelPerKey;
    }

    public void setMinIdelPerKey(Integer minIdelPerKey) {
        this.minIdelPerKey = minIdelPerKey;
    }

    public Integer getMaxIdelPerKey() {
        return maxIdelPerKey;
    }

    public void setMaxIdelPerKey(Integer maxIdelPerKey) {
        this.maxIdelPerKey = maxIdelPerKey;
    }
}
