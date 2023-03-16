package com.wisecoach.gatewayplus.session;

import io.grpc.ManagedChannel;
import org.hyperledger.fabric.client.*;
import org.hyperledger.fabric.client.identity.Identity;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Gateway} 的一个装饰器，会去为原来的 GatewayImpl 的默认实现提供一个对 {@link Network} 的缓存
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午7:43
 * {@code @version:} 1.0.0
 */


public class GatewaySession implements Gateway{

    /**
     * 被装饰的gateway对象
     */
    private final Gateway gateway;
    /**
     * 持有channel，方便关闭channel
     */
    private final ManagedChannel channel;
    /**
     * 要缓存的networks
     */
    private final Map<String, Network> networks = new HashMap<>();

    GatewaySession(Gateway gateway, ManagedChannel channel) {
        this.gateway = gateway;
        this.channel = channel;
    }

    /**
     * 提供了缓存功能
     * @return network
     */
    @Override
    public Network getNetwork(String channelName) {
        Network network = networks.get(channelName);
        if (network == null) {
            Network newNetwork = gateway.getNetwork(channelName);
            network = new NetworkSession(newNetwork);
            networks.put(channelName, network);
        }
        return network;
    }

    @Override
    public Identity getIdentity() {
        return gateway.getIdentity();
    }

    @Override
    public Proposal newSignedProposal(byte[] bytes, byte[] bytes1) {
        return gateway.newSignedProposal(bytes, bytes1);
    }

    @Override
    public Proposal newProposal(byte[] bytes) {
        return gateway.newProposal(bytes);
    }

    @Override
    public Transaction newSignedTransaction(byte[] bytes, byte[] bytes1) {
        return gateway.newSignedTransaction(bytes, bytes1);
    }

    @Override
    public Transaction newTransaction(byte[] bytes) {
        return gateway.newTransaction(bytes);
    }

    @Override
    public Commit newSignedCommit(byte[] bytes, byte[] bytes1) {
        return gateway.newSignedCommit(bytes, bytes1);
    }

    @Override
    public Commit newCommit(byte[] bytes) {
        return gateway.newCommit(bytes);
    }

    @Override
    public ChaincodeEventsRequest newSignedChaincodeEventsRequest(byte[] bytes, byte[] bytes1) {
        return gateway.newSignedChaincodeEventsRequest(bytes, bytes1);
    }

    @Override
    public ChaincodeEventsRequest newChaincodeEventsRequest(byte[] bytes) {
        return gateway.newChaincodeEventsRequest(bytes);
    }

    @Override
    public BlockEventsRequest newSignedBlockEventsRequest(byte[] bytes, byte[] bytes1) {
        return gateway.newSignedBlockEventsRequest(bytes, bytes1);
    }

    @Override
    public BlockEventsRequest newBlockEventsRequest(byte[] bytes) {
        return gateway.newBlockEventsRequest(bytes);
    }

    @Override
    public FilteredBlockEventsRequest newSignedFilteredBlockEventsRequest(byte[] bytes, byte[] bytes1) {
        return gateway.newSignedFilteredBlockEventsRequest(bytes, bytes1);
    }

    @Override
    public FilteredBlockEventsRequest newFilteredBlockEventsRequest(byte[] bytes) {
        return gateway.newFilteredBlockEventsRequest(bytes);
    }

    @Override
    public BlockAndPrivateDataEventsRequest newSignedBlockAndPrivateDataEventsRequest(byte[] bytes, byte[] bytes1) {
        return gateway.newSignedBlockAndPrivateDataEventsRequest(bytes, bytes1);
    }

    @Override
    public BlockAndPrivateDataEventsRequest newBlockAndPrivateDataEventsRequest(byte[] bytes) {
        return gateway.newBlockAndPrivateDataEventsRequest(bytes);
    }

    /**
     * 关闭channel，如果是缓存channel就会返回缓冲池
     */
    @Override
    public void close() {
        channel.shutdown();
        gateway.close();
    }
}
