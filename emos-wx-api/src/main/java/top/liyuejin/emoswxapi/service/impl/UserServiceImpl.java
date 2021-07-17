package top.liyuejin.emoswxapi.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import top.liyuejin.emoswxapi.dao.TbUserDao;
import top.liyuejin.emoswxapi.exception.EmosException;
import top.liyuejin.emoswxapi.service.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Resource
    private TbUserDao userDao;

    @Override
    public String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        var map = new HashMap<String, Object>();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        var response = HttpUtil.post(url, map);
        var json = JSONUtil.parseObj(response);
        var openId = json.getStr("openid");
        if (null == openId || 0 == openId.length()) {
            throw new RuntimeException("临时登陆凭证错误");
        }
        return openId;
    }

    @Override
    public int register(String registerCode, String code, String nickName, String photo) {
        // 如果邀请码是000000，代表是超级管理员
        if ("000000".equals(registerCode)) {
            // 查询是否存在超级管理员
            var b = userDao.haveRootUser();
            if (userDao.haveRootUser()) {
                throw new EmosException("无法绑定超级管理员账号");
            }
            // 绑定到ROOT
            var openId = getOpenId(code);
            var map = new HashMap<String, Object>();
            map.put("openId", openId);
            map.put("nickName", nickName);
            map.put("photo", photo);
            map.put("role", "[0]");
            map.put("status", 1);
            map.put("createTime", new Date());
            map.put("root", true);
            userDao.insertUser(map);
            return userDao.getIdByOpenId(openId);
        }
        return 0;
    }

    @Override
    public Set<String> getUserPermissions(int userId) {
        return userDao.getUserPermissions(userId);
    }
}
