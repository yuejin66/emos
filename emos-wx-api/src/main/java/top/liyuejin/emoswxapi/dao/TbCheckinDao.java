package top.liyuejin.emoswxapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface TbCheckinDao {

    Integer haveCheckin(HashMap<String, Object> map);
}