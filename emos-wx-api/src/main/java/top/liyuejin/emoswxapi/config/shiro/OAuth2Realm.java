package top.liyuejin.emoswxapi.config.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;
import top.liyuejin.emoswxapi.pojo.TbUser;
import top.liyuejin.emoswxapi.service.UserService;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.locks.Lock;

@Configuration
public class OAuth2Realm extends AuthorizingRealm {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权
     * （验证权限时调用）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        TbUser user = (TbUser) principalCollection.getPrimaryPrincipal();
        Integer userId = user.getId();
        // 查询用户权限列表
        Set<String> permissions = userService.getUserPermissions(userId);
        return new SimpleAuthorizationInfo(permissions);
    }

    /**
     * 认证
     * （登陆时调用）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        int userId = jwtUtil.getUserId(token);
        TbUser user = userService.getUserById(userId);
        if (null == user) { // 离职等原因
            throw new LockedAccountException("该账号已被冻结，请联系管理员");
        }
        return new SimpleAuthenticationInfo(user, token, getName());
    }
}
