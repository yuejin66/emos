package top.liyuejin.emoswxapi.config.shiro;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import top.liyuejin.emoswxapi.common.util.Result;

import javax.annotation.Resource;

/**
 * 拦截所有Web方法的返回值，并判断是否需要刷新生成新的token
 */
@Aspect
@Component
public class TokenAspect {

    @Resource
    private ThreadLocalToken threadLocalToken;

    @Pointcut("execution(public * top.liyuejin.emoswxapi.controller.*.*(..))")
    public void aspect() {}

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Result result = (Result) point.proceed();
        String token = threadLocalToken.getToken();
        // 如果ThreadLocal中存在token，则说明是更新的token
        if (null != token) {
            result.put("token", token);
            threadLocalToken.clear();
        }
        return result;
    }
}
