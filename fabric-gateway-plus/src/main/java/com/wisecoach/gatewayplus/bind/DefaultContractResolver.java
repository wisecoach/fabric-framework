package com.wisecoach.gatewayplus.bind;

import org.bouncycastle.util.Strings;

import java.util.Arrays;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午12:20
 * {@code @version:} 1.0.0
 */


public class DefaultContractResolver implements ContractResolver {

    @Override
    public String[] resolveArgs(Object[] args) {
        return (String[]) Arrays.stream(args).map(Object::toString).toArray();
    }

    @Override
    public Object resolveResult(byte[] result) {
        return Strings.fromByteArray(result);
    }
}
