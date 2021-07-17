package top.liyuejin.emoswxapi.service;

import java.util.Set;

public interface UserService {

    String getOpenId(String code);

    int register(String registerCode, String code, String nickName, String photo);

    Set<String> getUserPermissions(int userId);
}