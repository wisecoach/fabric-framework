package com.wisecoach.gatewayplus.spring.boot.configuration;

import com.wisecoach.gatewayplus.connection.GrpcConnSource;
import com.wisecoach.gatewayplus.connection.GrpcConnSourceDelegate;
import com.wisecoach.gatewayplus.connection.PooledGrpcConnSource;
import io.grpc.ManagedChannel;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 上午11:58
 * {@code @version:} 1.0.0
 */

@Configuration
@EnableConfigurationProperties(GatewayPlusConnProperties.class)
public class GatewayPlusConnAutoConfiguration {

    private final GatewayPlusConnProperties properties;

    public GatewayPlusConnAutoConfiguration(GatewayPlusConnProperties properties) {
        this.properties = properties;
    }

    // ------------------- connection ---------------------

    /**
     * 这个注入到容器中，主要是为了注册自定义的GrpcConn管理策略
     */
    @Bean
    public GrpcConnSourceDelegate grpcConnSourceDelegate() {
        return new GrpcConnSourceDelegate();
    }

    /**
     * 默认采用池化GRPC连接源，如果不用，就使用默认的委派GRPC源
     */
    @Bean
    @Primary
    public GrpcConnSource grpcConnSource(GrpcConnSourceDelegate delegate) {
        if (properties.getUsePool() == null || properties.getUsePool()) {
            GenericKeyedObjectPoolConfig<ManagedChannel> config = new GenericKeyedObjectPoolConfig<>();
            if (properties.getMaxTotal() != null) {
                config.setMaxTotal(properties.getMaxTotal());
            }
            if (properties.getMaxTotalPerKey() != null) {
                config.setMaxTotalPerKey(properties.getMaxTotalPerKey());
            }
            if (properties.getMaxIdelPerKey() != null) {
                config.setMaxIdlePerKey(properties.getMaxIdelPerKey());
            }
            if (properties.getMinIdelPerKey() != null) {
                config.setMinIdlePerKey(properties.getMinIdelPerKey());
            }
            return new PooledGrpcConnSource(delegate);
        } else {
            return delegate;
        }
    }
}
