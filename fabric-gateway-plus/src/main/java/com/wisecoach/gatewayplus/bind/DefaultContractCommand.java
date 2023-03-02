package com.wisecoach.gatewayplus.bind;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午9:29
 * {@code @version:} 1.0.0
 */


public class DefaultContractCommand implements ContractCommand {
    
    private final String name;
    private final String channelName;
    private final String contractName;
    private final String transactionName;
    private final ContractCommandType contractCommandType;

    public DefaultContractCommand(String name, String channelName, String contractName, String transactionName, ContractCommandType contractCommandType) {
        this.name = name;
        this.channelName = channelName;
        this.contractName = contractName;
        this.transactionName = transactionName;
        this.contractCommandType = contractCommandType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getChannelName() {
        return channelName;
    }

    @Override
    public String getContractName() {
        return contractName;
    }

    @Override
    public String getTransactionName() {
        return transactionName;
    }

    @Override
    public ContractCommandType getContractCommandType() {
        return contractCommandType;
    }
}
