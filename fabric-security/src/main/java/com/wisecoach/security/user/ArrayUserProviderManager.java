package com.wisecoach.security.user;

import com.wisecoach.security.userinfo.UserInfo;
import com.wisecoach.util.Assert;

import java.util.ArrayList;

/**
 * 采用数组存储provider的一个实现
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 下午1:24
 * {@code @version:} 1.0.0
 */


public class ArrayUserProviderManager implements UserProviderManager {
    // 此为一个按照优先级从高到低排序的数组
    private final ArrayList<UserProvider> providers = new ArrayList<>();

    @Override
    public User getUser(UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo不可为null");
        Assert.state(providers.size() > 0, "请至少注册一个provider");
        for (UserProvider userProvider : providers) {
            if (userProvider.support(userInfo)) {
                User user = userProvider.getUser(userInfo);
                if (user != null) return user;
            }
        }
        return null;
    }

    @Override
    public User getUser(UserInfo userInfo, Class<? extends UserProvider>[] classes) {
        Assert.notEmpty(classes, "请至少提供一个userProvider");
        if (classes != null && classes.length > 0) {
            UserProvider[] spec = (UserProvider[]) providers.stream().filter((provider) -> {
                for (Class<? extends UserProvider> specProviderClazz : classes) {
                    if (specProviderClazz.isInstance(provider)) {
                        return true;
                    }
                }
                return false;
            }).toArray();
            for (UserProvider userProvider : spec) {
                if (userProvider.support(userInfo)) {
                    User user = userProvider.getUser(userInfo);
                    if (user != null) return user;
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<UserProvider> getProviders() {
        return providers;
    }

    @Override
    public void register(UserProvider userProvider) {
        // 加入并根据优先级排序
        providers.add(userProvider);
        providers.sort((a, b) -> b.getPriority() - a.getPriority());
    }
}
