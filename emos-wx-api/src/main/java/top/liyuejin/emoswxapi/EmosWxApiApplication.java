package top.liyuejin.emoswxapi;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import top.liyuejin.emoswxapi.config.SystemConstants;
import top.liyuejin.emoswxapi.dao.SysConfigDao;
import top.liyuejin.emoswxapi.pojo.SysConfig;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

@SpringBootApplication
@ServletComponentScan
public class EmosWxApiApplication {

    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private SystemConstants systemConstants;

    private static final Logger log = LoggerFactory.getLogger(EmosWxApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EmosWxApiApplication.class, args);
    }

    @PostConstruct
    public void Init() {
        List<SysConfig> params = sysConfigDao.getAllParam();
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
