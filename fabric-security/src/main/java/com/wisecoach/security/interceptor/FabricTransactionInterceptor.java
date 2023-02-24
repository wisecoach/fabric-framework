package com.wisecoach.security.interceptor;

import com.wisecoach.security.annotation.FabricTransaction;
import com.wisecoach.security.user.*;
import com.wisecoach.security.userinfo.UserInfo;
import com.wisecoach.security.userinfo.UserInfoProvider;
import com.wisecoach.util.Assert;
import com.wisecoach.util.ObjectUtils;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * CGLib的拦截器，用于强化被 {@link FabricTransaction} 注释的Service方法
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午2:20
 * {@code @version:} 1.0.0
 */
public class FabricTransactionInterceptor implements MethodInterceptor {

    private final Logger logger = Logger.getLogger(FabricTransactionInterceptor.class.getName());
    private UserProviderManager userProviderManager;
    private UserInfoProvider userInfoProvider;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        FabricTransaction fabricTransaction = method.getAnnotation(FabricTransaction.class);
        if (fabricTransaction != null) {
            // 获取userInfo
            logger.trace("开始获取userinfo");
            UserInfo userInfo = userInfoProvider.getUserInfo(obj, method, args);
            Assert.notNull(userInfo, "取得UserInfo失败，请通过自定义UserInfoProvider或存入UserInfoContext的方式存入userInfo");

            // 取得user
            logger.trace("开始获取user");
            User user = null;
            try {
                if (!ObjectUtils.isEmpty(fabricTransaction.providers())) {
                    user = userProviderManager.getUser(userInfo, fabricTransaction.providers());
                } else {
                    user = userProviderManager.getUser(userInfo);
                }
            } catch (UserException e) {
                logger.error("未成功获取user：\n" + e);
                throw e;
            }

            // 存取user
            logger.trace("存储user");
            UserContext userContext = UserContextHolder.getContext();
            userContext.setUser(user);
            UserContextHolder.setContext(userContext);

            // 调用被加强方法
            logger.trace("调用被加强方法");
            return proxy.invokeSuper(obj, args);
        } else {
            return proxy.invokeSuper(obj, args);
        }
    }
}
