package com.wisecoach.service.impl;

import com.wisecoach.gatewayplus.annotation.ChaincodeTransaction;
import com.wisecoach.gatewayplus.session.GatewayContextHolder;
import com.wisecoach.gatewayplus.spring.annotation.ChaincodeService;
import com.wisecoach.security.annotation.FabricTransaction;
import com.wisecoach.security.spring.annotation.FabricService;
import com.wisecoach.security.user.User;
import com.wisecoach.security.user.UserContextHolder;
import com.wisecoach.service.TestService;
import org.hyperledger.fabric.client.Gateway;
import org.springframework.stereotype.Service;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/25 下午3:05
 * {@code @version:} 1.0.0
 */

@FabricService
// @ChaincodeService
@Service
public class TestServiceImpl implements TestService {

    @Override
    @FabricTransaction
    @ChaincodeTransaction
    public void test() {
        User user = UserContextHolder.getContext().getUser();
        Gateway gateway = GatewayContextHolder.getContext().getGateway();
        System.out.println(gateway);
    }
}
