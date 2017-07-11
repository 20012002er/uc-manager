package com.jia16.uc.service;

import com.jia16.uc.domain.User;
import com.jia16.uc.inter.UserService;
import com.jia16.uc.repository.UserRepository;
import com.jia16.uc.util.PasswordEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lazyb on 2017/7/7.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(String phone, String password) {
        User user = userRepository.findByPhone(phone);
        if (user != null) {
            logger.error("phone is exist");
            return null;
        }
        String name = "jsl" + System.currentTimeMillis();
        return userRepository.save(new User(name, phone, PasswordEncryption.BCRYPT.encrypt(password)));
    }

    @Override
    public User login(String phone, String password) {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            logger.error("phone is error");
            return null;
        }
        if (!PasswordEncryption.BCRYPT.check(password, user.getPassword())) {
            logger.error("password is error");
            return null;
        }
        return user;
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
}
