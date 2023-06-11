package com.wisecoach.gatewayplus.spring.boot.annotation;

import com.wisecoach.gatewayplus.spring.boot.configuration.DisabledGatewayPlusAutoConfiguration;
import com.wisecoach.gatewayplus.spring.boot.configuration.FabricGatewayPlusAutoConfigure;
import com.wisecoach.gatewayplus.spring.boot.configuration.GatewayPlusConnAutoConfiguration;
import com.wisecoach.gatewayplus.spring.boot.configuration.GatewayPlusPoolAutoConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 *
 * {@code @author:} wisecoach
 * {@code @date:} 2023/6/11 下午2:20
 * {@code @version:} 1.0.0
 */


public class GatewayPlusConfigurationSelector implements ImportSelector {

    private static final String name = "mode";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String annoName = EnableFabricGatewayPlus.class.getName();
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(annoName));
        if (attributes == null) {
            throw new IllegalArgumentException(String.format(
                    "@%s is not present on importing class '%s' as expected",
                    annoName, importingClassMetadata.getClassName()));
        }
        FabricGatewayPlusMode mode = attributes.getEnum(name);
        String[] imports = selectImports(mode);
        if (imports == null) {
            throw new IllegalArgumentException("Unknown AdviceMode: " + mode);
        }
        return imports;
    }

    public String[] selectImports(FabricGatewayPlusMode mode) {
        switch (mode) {
            case ENABLE:
                return new String[] {
                        FabricGatewayPlusAutoConfigure.class.getName(),
                        GatewayPlusConnAutoConfiguration.class.getName(),
                        GatewayPlusPoolAutoConfiguration.class.getName()
                };
            case DISABLE:
                return new String[] {
                        DisabledGatewayPlusAutoConfiguration.class.getName()
                };
            default:
                return null;
        }
    }
}
