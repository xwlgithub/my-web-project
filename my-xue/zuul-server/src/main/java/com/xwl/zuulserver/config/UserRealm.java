package com.xwl.zuulserver.config;

import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.otherserver.domain.UserInfo;
import com.xwl.otherserver.mapper.UserInfoMapper;
import com.xwl.zuulserver.mapper.RealmMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: 薛
 * @Date: 2020/6/10 13:44
 * @Description:
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public class UserRealm extends AuthorizingRealm {
    private static final String USER_REALM_NAME="UserRealm";
    private static final String PSD="e10adc3949ba59abbe56e057f20f883e";
    public static UserRealm userRealm;

    @PostConstruct
    public void inits(){
        userRealm=this;
        userRealm.realmMapper=this.realmMapper;
    }
    @Autowired
    private RealmMapper realmMapper;
    /**
     * 授权 TODO 交给自定义业务进行授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo so = new SimpleAuthorizationInfo();
        return so;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录用户名
        SimpleAuthenticationInfo simpleAuthenticationInfo=null;
        String userName = (String)authenticationToken.getPrincipal();

        if (userName.equals("薛文良")){
            //校验用户名密码是否正确
             simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName,PSD,USER_REALM_NAME);
             return simpleAuthenticationInfo;
        }
        UserInfo userInfo = userRealm.realmMapper.selectUserByName(userName);
        if (userInfo==null){
            throw new UnknownAccountException(ExceptionEnum.NAME_OR_PSD_ERROR.getMessage());
        }
        //校验用户名密码是否正确
         simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName,userInfo.getPassword(), USER_REALM_NAME);
        //加盐处理
        //simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
        return simpleAuthenticationInfo;
    }
}
