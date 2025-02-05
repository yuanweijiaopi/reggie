import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName Test
 * @Description TODO
 * @Author aql
 * @Date 2025/1/22 17:59
 * @Version 1.0
 **/
@Slf4j
public class Test {

    private static final String SECRET = "yourSecretKey";
    private static final int EXPIRE_SECONDS = 3600; // 例如：1小时
    private static final String TOKEN_PREFIX = "Bearer";

    public static String generateToken(Map<String, Object> claims) {
        io.jsonwebtoken.JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ", "JWT");
        builder.setHeaderParam("alg", "HS512");
        builder.setSubject("user info");
        builder.addClaims(claims);
        builder.signWith(SignatureAlgorithm.HS512, SECRET);
        builder.setExpiration(DateUtil.offsetSecond(new Date(), EXPIRE_SECONDS));
        String jwt = builder
                .compact();
        return  jwt;
    }

    public static Jws<Claims> parseToken(String token) {
        // 去掉前缀 "Bearer_"
        String jwtToken = token.replaceFirst(TOKEN_PREFIX + "_", "");

        // 解析 JWT 并验证签名
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SECRET) // 使用相同的密钥进行签名验证
                .parseClaimsJws(jwtToken);

        return claims;
    }

@org.junit.jupiter.api.Test
        public void test() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", "exampleUser");
        claims.put("role", "admin");

        String token = generateToken(claims);
//        System.out.println(token);

        Jws<Claims> parseToken = parseToken(token);
    Claims body = parseToken.getBody();
    System.out.println(body);

}

@org.junit.jupiter.api.Test
    public void test2() {
        Boolean a = true;
        Boolean b = false;

        if (a && b) {
            System.out.println("true");
        }else {
            System.out.println("false");
        }

    if (a || b) {
        System.out.println("true");
    }else {
        System.out.println("false");
    }

}

@org.junit.jupiter.api.Test
    public void test3() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name","");
    jsonObject.put("age",18);

    //将JSONObject转换成JSON字符串
    String jsonString = JSON.toJSONString(jsonObject);
    System.out.println(jsonString);

    //将JSON字符串解析为 JSONObject
    JSONObject parseObject = JSON.parseObject(jsonString);
    System.out.println(parseObject);

}

@org.junit.jupiter.api.Test
    public void test4() {
        String msg = "这是一段 没有意思 的话";
        String[] split = msg.split(" ");
        String s = split[2] + "已存在";
    System.out.println("split:"+Arrays.toString(split));
    System.out.println("s:"+s);


    String collect = Arrays.stream(split)
            .map(String::trim)
            .collect(Collectors.joining(","));
    System.out.println("collect:"+collect);

}
}
