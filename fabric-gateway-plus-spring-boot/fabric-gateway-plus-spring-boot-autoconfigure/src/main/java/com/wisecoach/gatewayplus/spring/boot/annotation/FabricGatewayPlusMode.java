package com.wisecoach.gatewayplus.spring.boot.annotation;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/6/11 下午2:11
 * {@code @version:} 1.0.0
 */

public enum FabricGatewayPlusMode {
    // 启用框架
    ENABLE,
    // 不启用框架，对于ContractMapper，会使用假的实现，不需要修改代码
    DISABLE,
}
