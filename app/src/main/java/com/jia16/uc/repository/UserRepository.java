package com.jia16.uc.repository;

import com.jia16.uc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lazyb on 2017/7/7.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByPhone(String phone);

}
