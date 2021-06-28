package top.liyuejin.emoswxapi.dao;

import org.apache.ibatis.annotations.Mapper;
import top.liyuejin.emoswxapi.pojo.TbHolidays;

@Mapper
public interface TbHolidaysMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbHolidays record);

    int insertSelective(TbHolidays record);

    TbHolidays selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbHolidays record);

    int updateByPrimaryKey(TbHolidays record);
}