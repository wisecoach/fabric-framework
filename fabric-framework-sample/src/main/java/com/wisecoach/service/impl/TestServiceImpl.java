package com.wisecoach.service.impl;

import com.wisecoach.contract.BasicContract;
import com.wisecoach.entity.co.AssetCO;
import com.wisecoach.gatewayplus.annotation.ChaincodeTransaction;
import com.wisecoach.gatewayplus.transaction.TransactionStrategy;
import com.wisecoach.security.annotation.FabricTransaction;
import com.wisecoach.security.spring.annotation.FabricService;
import com.wisecoach.service.TestService;
import com.wisecoach.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/25 下午3:05
 * {@code @version:} 1.0.0
 */

@FabricService
public class TestServiceImpl implements TestService {

    @Autowired
    private BasicContract basicContract;

    @Override
    @FabricTransaction
    @ChaincodeTransaction(TransactionStrategy.SUBMIT_ASYNC)
    public String test() {
        basicContract.initLedger();
        AssetCO assetCO = basicContract.readAsset("asset1");
        return JSONUtils.serialize(assetCO);
    }
}
