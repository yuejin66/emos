package top.liyuejin.emoswxapi.config.shiro;

import org.springframework.stereotype.Component;

/**
 * 用来存储新生成token的媒介类
 */
@Component
public class ThreadLocalToken {

    private final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void setToken(String token) {
        threadLocal.set(token);
    }

    public String getToken() {
        return threadLocal.get();
    }

    public void clear() {
        threadLocal.remove();
    }
}
