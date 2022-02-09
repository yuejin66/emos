package com.yuejin66.emoswxapi.dao;

import com.yuejin66.emoswxapi.pojo.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysConfigMapper {

    List<SysConfig> getAllParam();
}