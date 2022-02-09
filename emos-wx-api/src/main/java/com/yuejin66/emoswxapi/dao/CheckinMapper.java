package com.yuejin66.emoswxapi.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface CheckinMapper {

    Integer haveCheckin(HashMap<String, Object> map);
}