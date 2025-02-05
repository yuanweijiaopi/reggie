//import cn.hutool.core.date.DateTime;
//import cn.hutool.core.date.DateUtil;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.junit.jupiter.api.Test;
//import org.springframework.util.DigestUtils;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
///**
// * @ClassName DateTest
// * @Description TODO
// * @Author aql
// * @Date 2025/1/13 09:40
// * @Version 1.0
// **/
//public class DateTest {
//    private static final String SECRET = "secret";
//
//    private static final Integer EXPIRE_SECONDS = 60 * 60 * 24 * 7;
//    @Test
//    public void dateTest(){
//        DateTime dateTime = DateUtil.offsetSecond(new Date(), EXPIRE_SECONDS);
//        System.out.println(dateTime);
//    }
//
//
//
//    public String jwtTest(){
//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("sub", "这是一个主题");
//        claims.put("name", "John Doe");
//        claims.put("admin", true);
//
//        // 构建 JWT
//        String jwt = Jwts.builder()
//                .setClaims(claims)
//                .setIssuer("me")
//                .signWith(SignatureAlgorithm.HS256,SECRET)
//                .setExpiration(DateUtil.offsetSecond(new Date(), EXPIRE_SECONDS))
//                .compact();
//        return jwt;
//    }
//
//
//    @Test
//    public void parseJwt(){
//        String jwt = jwtTest();
//        System.out.println(jwt);
//
//        //解析令牌
//        Claims body = Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(jwt)
//                .getBody();
//
//        System.out.println(body);
//
//
//    }
//
//    @Test
//    public void test(){
//        double random = (Math.random()>0.5?1:-1);
//
//        System.out.println(random);
//
//    }
//
//    @Test
//    public void test2(){
//        String SECRET  = "secret";
//        System.out.println(DigestUtils.md5DigestAsHex(SECRET.getBytes()));
//
//    }
//}
