package top.liyuejin.emoswxapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liyuejin.emoswxapi.common.util.Result;
import top.liyuejin.emoswxapi.config.shiro.JwtUtil;
import top.liyuejin.emoswxapi.form.LoginForm;
import top.liyuejin.emoswxapi.form.RegisterForm;
import top.liyuejin.emoswxapi.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@Api("用户模块")
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Value("${emos.jwt.cache-expire}")
    private long cacheExpire;

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public Result register(@Valid @RequestBody RegisterForm registerForm) {
        int userId = userService.register(registerForm.getRegisterCode(),
                                            registerForm.getCode(),
                                            registerForm.getNickName(),
                                            registerForm.getPhoto());
        String token = jwtUtil.createToken(userId);
        Set<String> permissions = userService.getUserPermissions(userId);
        saveCacheToken(token, userId);
        return Result.success("用戶注冊成功").put("token", token).put("permission", permissions);
    }

    @PostMapping("/login")
    @ApiOperation("登陆系统")
    public Result login(@Valid @RequestBody LoginForm loginForm) {
        Integer userId = userService.login(loginForm.getCode());
        String token = jwtUtil.createToken(userId);
        Set<String> permissions = userService.getUserPermissions(userId);
        saveCacheToken(token, userId);
        return Result.success("登陆成功").put("token", token).put("permission", permissions);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户（测试Shiro权限）")
    @RequiresPermissions(value = {"ROOT", "USER:ADD"}, logical = Logical.OR)
    public Result addUser() {
        return Result.success("用户添加成功");
    }

    private void saveCacheToken(String token, int userId) {
        redisTemplate.opsForValue().set(token, userId + "", cacheExpire, TimeUnit.DAYS);
    }
}
