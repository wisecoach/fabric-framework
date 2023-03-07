package com.wisecoach.security.user;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 下午3:49
 * {@code @version:} 1.0.0
 */


public class DefaultUserAttribute implements UserAttribute {

    private final String name;
    private final Class<? extends UserProvider>[] providerClasses;

    public DefaultUserAttribute(String name, Class<? extends UserProvider>[] providerClasses) {
        this.name = name;
        this.providerClasses = providerClasses;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<? extends UserProvider>[] getProviderClasses() {
        return providerClasses;
    }
}
