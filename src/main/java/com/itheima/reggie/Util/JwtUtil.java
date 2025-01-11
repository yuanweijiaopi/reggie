package com.itheima.reggie.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @Author aql
 * @Date 2025/1/11 17:18
 * @Version 1.0
 **/
public class JwtUtil {
    // 替换为你的秘钥
    private static final String SECRET_KEY = "your_secret_key";
    // 1天的过期时间
    private static final long EXPIRATION_TIME = 86400000;

    public static String generateToken(Long employeeId) {
        return Jwts.builder()
                .setSubject(String.valueOf(employeeId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
