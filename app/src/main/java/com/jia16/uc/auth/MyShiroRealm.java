package com.jia16.uc.auth;

import com.jia16.uc.domain.User;
import com.jia16.uc.inter.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lazyb on 2017/7/10.
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken authToken = (UsernamePasswordToken) token;
        String phone = String.valueOf(authToken.getUsername());
        String pwd = String.valueOf(authToken.getPassword());
        User user = userService.login(phone, pwd);
        if (user == null) {
            throw new AccountException("账号或密码不正确！");
        }
        return new SimpleAuthenticationInfo(phone, pwd, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        String phone = super.getAvailablePrincipal(principals).toString();
        User user = userService.findByPhone(phone);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> roleSet = new HashSet<>();
            roleSet.add("admin");
            info.setRoles(roleSet);
            return info;
        }
        return null;
    }
}
