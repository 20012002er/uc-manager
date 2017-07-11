package com.jia16.uc.inter;

import com.jia16.uc.domain.User;

/**
 * Created by lazyb on 2017/7/7.
 */
public interface UserService {

    User register(String phone, String password);

    User login(String phone, String password);

    User findByPhone(String phone);

}
