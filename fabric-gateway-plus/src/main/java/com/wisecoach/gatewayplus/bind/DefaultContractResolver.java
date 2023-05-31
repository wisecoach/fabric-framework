package com.wisecoach.gatewayplus.bind;

import com.wisecoach.util.JSONUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午12:20
 * {@code @version:} 1.0.0
 */


public class DefaultContractResolver implements ContractResolver {

    @Override
    public String[] resolveArgs(Object[] args) {
        if (args == null) {
            return new String[]{};
        }
        return Arrays.stream(args).map(JSONUtils::serialize).collect(Collectors.toList()).toArray(new String[]{});
    }

    @Override
    public Object resolveResult(byte[] result, Method method) {
        if (method.getReturnType().isAssignableFrom(byte[].class)) {
            return result;
        }
        return JSONUtils.deserialize(result, method.getReturnType());
    }
}
