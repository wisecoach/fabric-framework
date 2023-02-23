package com.wisecoach.security.userinfo;

import java.io.Serializable;

/**
 * 该接口会为 {@link com.wisecoach.security.userprovider.UserProvider} 提供一些信息，用于取得 {@link com.wisecoach.security.user.User}
 * 该接口允许用户自由继承与实现，以提供额外所需的信息
 *
 * @see com.wisecoach.security.userprovider.UserProvider
 * @see com.wisecoach.security.user.User
 *
 * @author: wisecoach
 * @date: 2023/2/23 上午10:49
 * @version: 1.0.0
 */

public interface UserInfo extends Serializable {
    String getUsername();
    String getPassword();
}
