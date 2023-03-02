package com.wisecoach.gatewayplus.annotation;

import java.lang.annotation.*;

/**
 * 表明该类是一个会调用Chaincode的Service，如果一个Service要调用 {@link ContractMapper} 注解的类，那么必须使用该注解标注
 * {{@code @TODO} 这个接口应该放到spring模块中，那个模块才去识别这个}
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/2 下午6:53
 * {@code @version:} 1.0.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChaincodeService {

}
