package top.liyuejin.emoswxapi.dao;

import org.apache.ibatis.annotations.Mapper;
import top.liyuejin.emoswxapi.pojo.TbCheckin;

@Mapper
public interface TbCheckinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbCheckin record);

    int insertSelective(TbCheckin record);

    TbCheckin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbCheckin record);

    int updateByPrimaryKey(TbCheckin record);
}