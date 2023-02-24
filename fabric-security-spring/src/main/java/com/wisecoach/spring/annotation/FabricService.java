package com.wisecoach.spring.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 指明一个Service对象为一个FabricService，表示它可能存在 {@link com.wisecoach.security.annotation.FabricTransaction} 方法，
 * 被标注的类才会被处理
 * 还有就是帮你标注 {@link Service} 了
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/24 下午4:07
 * {@code @version:} 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface FabricService {
}
