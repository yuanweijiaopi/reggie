//package com.itheima.reggie.interceptors;
//
//import com.itheima.reggie.util.JwtUtil;
//import com.itheima.reggie.util.ThreadLocalUtil;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
///**
// * @ClassName LoginInterceptors
// * @Description TODO
// * @Author aql
// * @Date 2025/1/13 15:41
// * @Version 1.0
// **/
//@Component
//public class LoginInterceptors implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //令牌验证
//        String token = request.getHeader("Authorization");
//
//        // 检查令牌是否存在
//        if (token == null || token.isEmpty()) {
//            // 设置状态为 401
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            // 拦截请求
//            return false;
//        }
//
//        //验证token
//        Map<String, Object> claims = JwtUtil.resolveToken(token);
//        try {
//            claims = JwtUtil.resolveToken(token);
//        } catch (Exception e) {
//            // 设置状态为 401
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            // 拦截请求
//            return false;
//        }
//        ThreadLocalUtil.set(claims);
//        return true;
//    }
//}
