package top.liyuejin.emoswxapi.dao;

import org.apache.ibatis.annotations.Mapper;
import top.liyuejin.emoswxapi.pojo.SysConfig;

import java.util.List;

@Mapper
public interface SysConfigDao {

    List<SysConfig> getAllParam();
}