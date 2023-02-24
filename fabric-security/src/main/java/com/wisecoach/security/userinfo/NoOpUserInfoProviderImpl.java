package com.wisecoach.security.userinfo;

import java.lang.reflect.Method;

/**
 * 啥也不做的默认实现，主要是允许用户不使用该接口来取得UserInfo，
 * 而是通过 {@link UserInfoContextHolder#setContext(UserInfoContext)} 的方式直接存入UserInfo并取得
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午2:56
 * {@code @version:} 1.0.0
 */


public class NoOpUserInfoProviderImpl extends AbstractUserInfoProvider {

    @Override
    protected UserInfo fetchUserInfo(Object obj, Method method, Object[] args) {
        return null;
    }
}
