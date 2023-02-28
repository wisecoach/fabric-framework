package com.wisecoach.service.impl;

import com.wisecoach.security.annotation.FabricTransaction;
import com.wisecoach.security.spring.annotation.FabricService;
import com.wisecoach.security.user.User;
import com.wisecoach.security.user.UserContextHolder;
import com.wisecoach.service.TestService;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/25 下午3:05
 * {@code @version:} 1.0.0
 */

@FabricService
public class TestServiceImpl implements TestService {

    @Override
    @FabricTransaction
    public void test() {
        User user = UserContextHolder.getContext().getUser();
    }
}
