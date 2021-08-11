package top.liyuejin.emoswxapi.service;

import top.liyuejin.emoswxapi.pojo.TbUser;

import java.util.Set;

public interface UserService {

    Integer login(String code);

    int register(String registerCode, String code, String nickName, String photo);

    String getOpenId(String code);

    Set<String> getUserPermissions(int userId);

    TbUser getUserById(int userId);
}
