package com.wisecoach.gatewayplus.session;

import com.wisecoach.util.StringUtils;
import io.grpc.CallOptions;
import org.hyperledger.fabric.client.*;
import org.hyperledger.fabric.protos.common.Block;
import org.hyperledger.fabric.protos.peer.BlockAndPrivateData;
import org.hyperledger.fabric.protos.peer.FilteredBlock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.UnaryOperator;

/**
 * 对 {@link Network} 的一个装饰器，实现了对 {@link Contract} 的缓存
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午7:56
 * {@code @version:} 1.0.0
 */


public class NetworkSession implements Network{

    /**
     * 要被装饰的对象
     */
    private final Network network;

    /**
     * 要缓存的contracts
     */
    private final Map<String, Contract> contracts = new ConcurrentHashMap<>();

    NetworkSession(Network network) {
        this.network = network;
    }

    @Override
    public Contract getContract(String chaincodeName) {
        Contract contract = contracts.get(chaincodeName);
        if (contract == null) {
            contract = network.getContract(chaincodeName);
            contracts.put(chaincodeName, contract);
        }
        return contract;
    }

    @Override
    public Contract getContract(String chaincodeName, String contractName) {
        Contract contract = contracts.get(getKey(chaincodeName, contractName));
        if (contract == null) {
            contract = network.getContract(chaincodeName, contractName);
            contracts.put(getKey(chaincodeName, contractName), contract);
        }
        return contract;
    }

    private String getKey(String chaincodeName, String contractName) {
        if (StringUtils.hasLength(contractName)) {
            return chaincodeName + ":" + contractName;
        }
        return chaincodeName;
    }

    @Override
    public String getName() {
        return network.getName();
    }

    @Override
    public CloseableIterator<ChaincodeEvent> getChaincodeEvents(String chaincodeName, UnaryOperator<CallOptions> options) {
        return network.getChaincodeEvents(chaincodeName, options);
    }

    @Override
    public ChaincodeEventsRequest.Builder newChaincodeEventsRequest(String chaincodeName) {
        return network.newChaincodeEventsRequest(chaincodeName);
    }

    @Override
    public CloseableIterator<Block> getBlockEvents(UnaryOperator<CallOptions> options) {
        return network.getBlockEvents(options);
    }

    @Override
    public BlockEventsRequest.Builder newBlockEventsRequest() {
        return network.newBlockEventsRequest();
    }

    @Override
    public CloseableIterator<FilteredBlock> getFilteredBlockEvents(UnaryOperator<CallOptions> options) {
        return network.getFilteredBlockEvents(options);
    }

    @Override
    public FilteredBlockEventsRequest.Builder newFilteredBlockEventsRequest() {
        return network.newFilteredBlockEventsRequest();
    }

    @Override
    public CloseableIterator<BlockAndPrivateData> getBlockAndPrivateDataEvents(UnaryOperator<CallOptions> options) {
        return network.getBlockAndPrivateDataEvents(options);
    }

    @Override
    public BlockAndPrivateDataEventsRequest.Builder newBlockAndPrivateDataEventsRequest() {
        return network.newBlockAndPrivateDataEventsRequest();
    }
}
