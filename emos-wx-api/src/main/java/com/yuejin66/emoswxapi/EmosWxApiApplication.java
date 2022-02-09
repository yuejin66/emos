package com.yuejin66.emoswxapi;

import cn.hutool.core.util.StrUtil;
import com.yuejin66.emoswxapi.dao.SysConfigMapper;
import com.yuejin66.emoswxapi.pojo.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import com.yuejin66.emoswxapi.config.system.SystemConstants;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

@SpringBootApplication
@ServletComponentScan
public class EmosWxApiApplication {

    @Resource
    private SysConfigMapper sysConfigMapper;

    @Resource
    private SystemConstants systemConstants;

    private static final Logger log = LoggerFactory.getLogger(EmosWxApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EmosWxApiApplication.class, args);
    }

    @PostConstruct
    public void Init() {
        List<SysConfig> params = sysConfigMapper.getAllParam();
        params.forEach(param -> {
            String key = StrUtil.toCamelCase(param.getParamKey());
            String value = param.getParamValue();
            try {
                Field field = systemConstants.getClass().getDeclaredField(key);
                field.set(systemConstants, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("执行异常", e);
            }
        });
    }
}
