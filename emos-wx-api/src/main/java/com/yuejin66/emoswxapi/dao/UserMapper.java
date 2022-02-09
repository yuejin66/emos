package com.yuejin66.emoswxapi.dao;

import com.yuejin66.emoswxapi.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Set;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    boolean haveRootUser();

    int insertUser(HashMap map);

    Integer getIdByOpenId(String openId);

    Set<String> getUserPermissions(int userId);

    User getUserById(int userId);
}