package com.wisecoach.gatewayplus.bind;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import org.bouncycastle.util.Strings;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午12:20
 * {@code @version:} 1.0.0
 */


public class DefaultContractResolver implements ContractResolver {

    private final Genson genson;

    public DefaultContractResolver() {
        genson = new GensonBuilder()
                .useIndentation(true)
                .useRuntimeType(true)
                .useClassMetadataWithStaticType(false)
                .useClassMetadata(true)
                .create();
    }

    @Override
    public String[] resolveArgs(Object[] args) {
        if (args == null) {
            return new String[]{};
        }
        return Arrays.stream(args).map(genson::serialize).collect(Collectors.toList()).toArray(new String[]{});
    }

    @Override
    public Object resolveResult(byte[] result, Method method) {
        return genson.deserialize(result, method.getReturnType());
    }
}
