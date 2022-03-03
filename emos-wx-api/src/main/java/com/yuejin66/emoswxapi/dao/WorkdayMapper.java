package com.yuejin66.emoswxapi.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkdayMapper {

    Integer queryTodayIsWorkday();
}