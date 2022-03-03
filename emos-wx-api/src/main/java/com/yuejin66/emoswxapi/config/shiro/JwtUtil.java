package com.yuejin66.emoswxapi.config.shiro;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${emos.jwt.secret}")
    private String secret;

    @Value("${emos.jwt.expire}")
    private int expire;

    /**
     * 生成并返回token
     */
    public String createToken(int userId) {
        Date date = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 5);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withClaim("userId", userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 解码token返回用户id
     */
    public int getUserId(String token) {
        return JWT.decode(token).getClaim("userId")
                .asInt();
    }

    /**
     * 验证token是否有效，无效则抛出RuntimeException异常
     */
    public void verifierToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWT.require(algorithm).build()
                .verify(token);
    }
}
