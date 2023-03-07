package com.wisecoach.security.user;

import com.wisecoach.security.annotation.FabricTransaction;
import com.wisecoach.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 注解的实现类，我们直接在这完成对注解的读取，并且生成UserAttribute，不考虑再做一层注解的解析层的抽象
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/7 上午11:39
 * {@code @version:} 1.0.0
 */


public class AnnotationUserAttributeSource extends AbstractUserAttributeSource {

    /**
     * 都是有资格的
     */
    @Override
    public boolean isCandidateClass(Class<?> targetClass) {
        return true;
    }

    @Override
    protected UserAttribute findUserAttribute(Method method) {
        FabricTransaction fabricTransaction = method.getAnnotation(FabricTransaction.class);
        if (fabricTransaction == null || !fabricTransaction.value()) {
            return null;
        }
        String name = fabricTransaction.name();
        if (!StringUtils.hasLength(name)) {
            name = method.getDeclaringClass().getSimpleName() + ":" + method.getName();
        }
        Class<? extends UserProvider>[] providers = fabricTransaction.providers();
        return new DefaultUserAttribute(name, providers);
    }
}
