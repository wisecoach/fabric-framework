package com.wisecoach.service.impl;

import com.wisecoach.contract.BasicContract;
import com.wisecoach.entity.co.AssetCO;
import com.wisecoach.gatewayplus.annotation.ChaincodeTransaction;
import com.wisecoach.gatewayplus.session.GatewayContext;
import com.wisecoach.gatewayplus.session.GatewayContextHolder;
import com.wisecoach.security.annotation.FabricTransaction;
import com.wisecoach.security.spring.annotation.FabricService;
import com.wisecoach.security.user.User;
import com.wisecoach.security.user.UserContext;
import com.wisecoach.security.user.UserContextHolder;
import com.wisecoach.service.TestService;
import org.checkerframework.checker.units.qual.A;
import org.hyperledger.fabric.client.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/25 下午3:05
 * {@code @version:} 1.0.0
 */

@FabricService
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private BasicContract basicContract;

    @Override
    @FabricTransaction
    @ChaincodeTransaction
    public void test() {
        // User user = UserContextHolder.getContext().getUser();
        // GatewayContext context = GatewayContextHolder.getContext();
        // System.out.println(context);
        // basicContract.initLedger();
        basicContract.readAsset("asset1");
    }
}
