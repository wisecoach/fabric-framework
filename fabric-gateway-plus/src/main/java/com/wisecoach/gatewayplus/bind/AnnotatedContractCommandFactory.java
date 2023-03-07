package com.wisecoach.gatewayplus.bind;

import com.wisecoach.gatewayplus.annotation.ContractMapper;
import com.wisecoach.gatewayplus.annotation.Evalute;
import com.wisecoach.gatewayplus.annotation.Submit;
import com.wisecoach.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 基于注解实现的合约命令工厂
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午2:27
 * {@code @version:} 1.0.0
 */


public class AnnotatedContractCommandFactory implements ContractCommandFactory {

    private String defaultChannelName;

    public AnnotatedContractCommandFactory() {
    }

    public AnnotatedContractCommandFactory(String defaultChannelName) {
        this.defaultChannelName = defaultChannelName;
    }

    @Override
    public ContractCommand getCommand(Class<?> mapperInterface, Method method) {
        ContractMapper contractMapper = mapperInterface.getAnnotation(ContractMapper.class);
        if (contractMapper == null) {
            throw new BindException("请确保要注册为ContractMapper的接口类使用@ContractMapper注解");
        }
        String channelName = contractMapper.channelName();
        // 如果没有设置，使用默认的channelName
        if (!StringUtils.hasLength(channelName)) {
            channelName = defaultChannelName;
        }
        // 如果默认channel没设置就报错
        if (!StringUtils.hasLength(channelName)) {
            throw new BindException("请设置该ContractMapper的channelName:" + mapperInterface.getName());
        }
        // 取得chaincodeName
        String chaincodeName = contractMapper.value();
        if (!StringUtils.hasLength(chaincodeName)) {
            throw new BindException("请设置ContractMapper的chaincodeName:" + mapperInterface.getName());
        }
        // 取得contractName
        String contractName = contractMapper.contractName();
        // 取得链码调用方式
        ContractCommandType type = null;
        String transactionName = null;
        Evalute evalute = method.getAnnotation(Evalute.class);
        Submit submit = method.getAnnotation(Submit.class);
        if (evalute != null) {
            type = ContractCommandType.EVALUATE;
            transactionName = evalute.value();
        } else if (submit != null) {
            type = ContractCommandType.SUBMIT;
            transactionName = submit.value();
        } else {
            throw new BindException("该方法需要使用注解@Evaluate或@Submit:" + method.getName());
        }
        // 取得transactionName
        if (!StringUtils.hasLength(transactionName)) {
            throw new BindException("该方法必须设置transactionName:" + method.getName());
        }
        return new DefaultContractCommand(method.getName(), channelName, chaincodeName, contractName, transactionName, type);
    }

    public String getDefaultChannelName() {
        return defaultChannelName;
    }

    public void setDefaultChannelName(String defaultChannelName) {
        this.defaultChannelName = defaultChannelName;
    }
}
