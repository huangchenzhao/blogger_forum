package com.ruixin.common.security;


import com.ruixin.bean.User;
import com.ruixin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 验证用户登录
 */
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    public UserRealm() {
        setName("UserRealm");
    }

    //权限资源角色
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) getAvailablePrincipal(principals);
        //String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.err.println("-------------------------------"+user.getUsername());
        //add Permission Resources
        //info.setStringPermissions(userService.findPermissions(username));
        //add Roles String[Set<String> roles]
        //info.setRoles(roles);
        return info;
    }

    //登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        String userName = upt.getUsername();
        //查询数据库
        User user = userService.findByAccount(userName);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (user.getStatus().equals(User.STATUS_AUDIT)){
            throw new LockedAccountException();
        }
        if(user.getStatus().equals(User.STATUS_DELETE)){
            throw new DisabledAccountException();
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),credentialsSalt, getName());
        return info;
    }
}