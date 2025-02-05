package com.itheima.reggie.filter;

import cn.hutool.core.text.AntPathMatcher;
import com.itheima.reggie.util.JwtUtil;
import com.itheima.reggie.util.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName LoginCheckFilter
 * @Description TODO
 * @Author aql
 * @Date 2025/1/20 22:03
 * @Version 1.0
 **/
//检查用户是否已经完成登录
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 每次重新启动，还是需要重新登录
        request.getSession().invalidate();

        //1.获取本次请求的url
        String requestURI = request.getRequestURI();


        //不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
//                "/backend/**",
//                "/front/**"
        };
        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3.如果不需要处理，则直接放行
        if (check) {
            filterChain.doFilter(request, response);
            return;
        }

        //4.判断登录状态，如果未登录，返回登录结果，如果已登录，则直接放行
//        if (request.getSession().getAttribute("employee") != null) {
//            Long employeeId = (Long) request.getSession().getAttribute("employeeId");
//            ThreadLocalUtil.set(employeeId);
//            filterChain.doFilter(request, response);
//            return;
//        }

        // 4. 从请求头中获取JWT令牌
        String token = request.getHeader("Authorization");

        // 5. 验证令牌
        if (token != null) {
            Long employeeId = validateToken(token);
            if (employeeId != null) {
                // 令牌有效，设置用户ID到ThreadLocal
                ThreadLocalUtil.set(employeeId);
                filterChain.doFilter(request, response);
                return;
            }
        }

        //5.如果未登录则返回未登录页面
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Unauthorized");
        log.info("拦截到请求：{}", request.getRequestURI());
    }

    /*
     * @Author vk
     * @Description //TODO 路径匹配，检查本次请求是否需要放行
     * @Date 14:45 2025/1/22
     * @Param 
     * @return 
     */
    public boolean check(String[] urls,String requestURL){
        return Arrays.stream(urls).anyMatch(url -> PATH_MATCHER.match(url, requestURL));
    }

    public Long validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("yourSecretKey")
                    .parseClaimsJws(token)
                    .getBody();
            return Long.parseLong(claims.getSubject());
        } catch (SignatureException e) {
            // 令牌无效
            return null;
        }
    }


}
