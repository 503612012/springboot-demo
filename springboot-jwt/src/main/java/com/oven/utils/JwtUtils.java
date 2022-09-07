package com.oven.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * JwtUtils
 *
 * @author Oven
 */
@Slf4j
public final class JwtUtils {

    /**
     * 存放token的请求头对应的key的名字
     */
    private static String headerKey = "token";

    /**
     * 加密的secret
     */
    private static String secret = "OvenSecret";

    /**
     * 过期时间，单位为秒
     */
    private static long expire = 15 * 60;

    /**
     * 生成token
     */
    public static String generateToken(Map<String, Object> map) {
        if (Objects.isNull(map)) {
            map = new HashMap<>();
        }
        Date expireDate = new Date(System.currentTimeMillis() + expire * 1000); // 过期时间
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")   // 设置头部信息
                .setClaims(map)                 // 装入自定义的用户信息
                .setExpiration(expireDate)      // token过期时间
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 校验token并解析token
     *
     * @return Claims：它继承了Map,而且里面存放了生成token时放入的用户信息
     */
    public static Claims verifyAndGetClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getHeaderKey() {
        return headerKey;
    }

}