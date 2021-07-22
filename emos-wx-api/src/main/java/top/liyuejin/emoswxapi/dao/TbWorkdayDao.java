package top.liyuejin.emoswxapi.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbWorkdayDao {

    Integer queryTodayIsWorkday();
}