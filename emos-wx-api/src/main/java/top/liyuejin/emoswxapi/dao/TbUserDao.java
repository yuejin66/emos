package top.liyuejin.emoswxapi.dao;

import org.apache.ibatis.annotations.Mapper;
import top.liyuejin.emoswxapi.pojo.TbUser;

import java.util.HashMap;

@Mapper
public interface TbUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    boolean haveRootUser();

    int insertUser(HashMap map);

    Integer selectIdByOpenId(String openId);
}