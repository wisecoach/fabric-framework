package com.wisecoach.gatewayplus.proxy;

import com.wisecoach.gatewayplus.bind.ContractCommand;
import com.wisecoach.gatewayplus.bind.ContractCommandType;
import com.wisecoach.gatewayplus.bind.ContractResolver;
import com.wisecoach.gatewayplus.session.GatewayContextHolder;
import com.wisecoach.gatewayplus.session.SessionException;
import com.wisecoach.gatewayplus.transaction.ContractExecutor;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.Network;

import java.lang.reflect.Method;

/**
 * ContractMapper实际强化方法
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/4 下午7:56
 * {@code @version:} 1.0.0
 */


public class MapperMethod {
    /**
     * 这个加强方法所对应的链码命令
     */
    private final ContractCommand command;
    /**
     * 合约执行器
     */
    private final ContractExecutor executor;
    /**
     * 合约参数解析器
     */
    private final ContractResolver resolver;

    public MapperMethod(ContractCommand command, ContractExecutor executor, ContractResolver resolver) {
        this.command = command;
        this.executor = executor;
        this.resolver = resolver;
    }

    /**
     * 加强流程如下:
     * 1. 取得 {@link GatewayContextHolder}的gateway
     * 2. 根据 command中的 chaincodeName, contractName, transactionName 取得对应的Contract
     * 3. 将Object参数，解析为对应的String参数
     * 4. 读取command中的contractCommandType，根据判断执行不同操作
     * 5. 执行evaluate或者submit
     * 6. 解析返回结果
     * 7. 返回结果
     */
    public Object execute(Method method, Object[] args) throws Exception {
        // 1
        Gateway gateway = GatewayContextHolder.getContext().getGateway();
        if (gateway == null) {
            throw new SessionException("需要在调用链码之前，在GatewayContextHolder中注入gateway对象；" +
                    "请判断是否给调用链码的Service方法加上对应的注解");
        }
        // 2
        Network network = gateway.getNetwork(command.getChannelName());
        Contract contract = network.getContract(command.getChaincodeName(), command.getContractName());
        // 3
        String[] strArgs = resolver.resolveArgs(args);
        Object ret = null;
        // 4
        if (ContractCommandType.EVALUATE.equals(command.getContractCommandType())) {
            // 5
            byte[] evaluate = executor.evaluate(contract, command.getTransactionName(), strArgs);
            // 6
            ret = resolver.resolveResult(evaluate, method);
        } else if (ContractCommandType.SUBMIT.equals(command.getContractCommandType())) {
            // 5
            byte[] submit = executor.submit(contract, command.getTransactionName(), strArgs);
            // 6
            ret = resolver.resolveResult(submit, method);
        }
        // 7
        return ret;
    }
}
