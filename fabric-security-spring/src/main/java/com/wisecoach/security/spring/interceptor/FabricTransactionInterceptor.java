package com.wisecoach.security.spring.interceptor;

import com.wisecoach.security.annotation.FabricTransaction;
import com.wisecoach.security.user.*;
import com.wisecoach.security.userinfo.UserInfo;
import com.wisecoach.security.userinfo.UserInfoContextHolder;
import com.wisecoach.security.userinfo.UserInfoProvider;
import com.wisecoach.util.Assert;
import com.wisecoach.util.ObjectUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * 用于强化被 {@link FabricTransaction} 注释的Service方法
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午2:20
 * {@code @version:} 1.0.0
 */
public class FabricTransactionInterceptor implements MethodInterceptor, Ordered {

    private final static int DEFAULT_PRIORITY = 5;

    private final Logger logger = Logger.getLogger(FabricTransactionInterceptor.class.getName());
    private final UserAttributeSource userAttributeSource;
    private final UserProviderManager userProviderManager;
    private final UserInfoProvider userInfoProvider;

    public FabricTransactionInterceptor(UserAttributeSource userAttributeSource, UserProviderManager userProviderManager, UserInfoProvider userInfoProvider) {
        this.userAttributeSource = userAttributeSource;
        this.userProviderManager = userProviderManager;
        this.userInfoProvider = userInfoProvider;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object obj = invocation.getThis();
        Method method = invocation.getMethod();
        Object[] args = invocation.getArguments();
        UserAttribute attribute = userAttributeSource.getUserAttribute(method, method.getDeclaringClass());
        if (attribute != null) {
            // 获取userInfo
            logger.trace("开始获取userinfo");
            UserInfo userInfo = userInfoProvider.getUserInfo(obj, method, args);
            Assert.notNull(userInfo, "取得UserInfo失败，请通过自定义UserInfoProvider或存入UserInfoContext的方式存入userInfo");

            // 取得user
            logger.trace("开始获取user");
            User user = null;
            try {
                if (!ObjectUtils.isEmpty(attribute.getProviderClasses())) {
                    user = userProviderManager.getUser(userInfo, attribute.getProviderClasses());
                } else {
                    user = userProviderManager.getUser(userInfo);
                }
            } catch (UserException e) {
                logger.error("未成功获取user：\n" + e);
                throw e;
            }

            // 存取user
            logger.trace("存储user");
            boolean needClear = true;
            UserContext userContext = UserContextHolder.getContext();
            if (userContext.getUser() != null) {
                needClear = false;
            } else {
                userContext.setUser(user);
                UserContextHolder.setContext(userContext);
            }

            // 调用被加强方法
            logger.trace("调用被加强方法");
            Object ret = invocation.proceed();
            // 清空两个信息的持有者
            if (needClear) {
                UserInfoContextHolder.clearContext();
                UserContextHolder.clearContext();
            }
            return ret;

        } else {
            return invocation.proceed();
        }
    }

    public UserAttributeSource getUserAttributeSource() {
        return userAttributeSource;
    }

    @Override
    public int getOrder() {
        return DEFAULT_PRIORITY;
    }
}
