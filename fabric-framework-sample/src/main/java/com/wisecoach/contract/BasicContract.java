package com.wisecoach.contract;

import com.wisecoach.entity.co.AssetCO;
import com.wisecoach.gatewayplus.annotation.ContractMapper;
import com.wisecoach.gatewayplus.annotation.Evalute;
import com.wisecoach.gatewayplus.annotation.Submit;
import org.springframework.stereotype.Repository;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 下午3:27
 * {@code @version:} 1.0.0
 */
@Repository
@ContractMapper("basic")
public interface BasicContract {
    @Evalute("ReadAsset")
    AssetCO readAsset(String assetId);

    @Submit("InitLedger")
    void initLedger();
}
