package top.liyuejin.emoswxapi.service;

public interface UserService {

    String getOpenId(String code);

    int register(String registerCode, String code, String nickName, String photo);

}
