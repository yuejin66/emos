package com.yuejin66.emoswxapi.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yuejin66.emoswxapi.common.util.Result;
import com.yuejin66.emoswxapi.config.shiro.JwtUtil;
import com.yuejin66.emoswxapi.service.CheckinService;

import javax.annotation.Resource;

/**
 * @author yuejin66
 */
@Api("签到模块")
@RestController
@RequestMapping("/checkin")
public class CheckinController {

    private final static Logger log = LoggerFactory.getLogger(CheckinController.class);

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private CheckinService checkinService;

    @GetMapping("/validCanCheckIn")
    @ApiOperation("查看用户今天是否可以签到")
    public Result validCanCheckIn(@RequestHeader("token") String token) {
        int userId = jwtUtil.getUserId(token);
        String result = checkinService.validCanCheckIn(userId, DateUtil.today());
        return Result.success(result);
    }
}
