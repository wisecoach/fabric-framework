package com.wisecoach.gatewayplus.bind;

import java.lang.reflect.Method;

/**
 * 合约命令工厂，根据接口类和方法去生成对应的合约命令
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/5 下午1:40
 * {@code @version:} 1.0.0
 */


public interface ContractCommandFactory {

    ContractCommand getCommand(Class<?> mapperInterface, Method method);

}
