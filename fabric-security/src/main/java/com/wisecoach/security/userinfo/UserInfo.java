package com.wisecoach.security.userinfo;

import com.wisecoach.security.user.UserProvider;

import java.io.Serializable;

/**
 * 该接口会为 {@link UserProvider} 提供一些信息，用于取得 {@link com.wisecoach.security.user.User}
 * 该接口允许用户自由继承与实现，以提供额外所需的信息
 *
 * @see UserProvider
 * @see com.wisecoach.security.user.User
 *
 * {@code @author:} wisecoach
 * {@code @date:} 2023/2/23 上午10:49
 * {@code @version:} 1.0.0
 */

public interface UserInfo extends Serializable {
    /**
     * 取得User的用户名
     * @return username
     */
    String getUsername();

    /**
     * 取得User的密码
     * @return password
     */
    String getPassword();

}
