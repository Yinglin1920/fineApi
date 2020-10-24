package com.example.demo.filters;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.AuthorizedUser;
import com.example.demo.common.CurrentUser;
import com.example.demo.common.Jwt;
import com.example.demo.common.ObjectHelper;
import lombok.SneakyThrows;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lyl
 * @version 1.0
 * @date 2020/10/11 17:07
 * @desc
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     *
     */
    private final static LinkedHashMap<String, String> ALLOW_NOTOKEN_URL = new LinkedHashMap<String, String>() {{
        put("/user/login", "登录");

    }};

    /**
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 不需要token的可以跳过
        String uri = request.getRequestURI();

        if (ALLOW_NOTOKEN_URL.containsKey(uri)) {
            filterChain.doFilter(request, response);
        }else {

            String token = request.getHeader("X-Auth-Token");
            if (ObjectHelper.isEmpty(token)) {
                throw new RuntimeException("缺少授权，禁止访问");
            } else {
                //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
                Boolean flag = Jwt.validToken(token);
                if (Boolean.FALSE.equals(flag)) {
                    throw new RuntimeException("登录已验证过期，请重新登录");
                }
                //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
                //todo 自动续期token

                // 缓存当前登录的用户
                cacheUser(token);

                // finish
                filterChain.doFilter(request, response);
            }
        }
    }

    /**
     * 缓存当前登录的用户
     *
     * @param token
     * @throws ParseException
     */
    private void cacheUser(String token) throws ParseException {
        Map<String, Object> info = Jwt.parseToken(token);
//        Long uid = Long.valueOf(info.get("uid").toString());
        JSON jobj = (JSON) JSON.toJSON(info.get("AuthUser"));
        AuthorizedUser authorizedUser = JSON.toJavaObject(jobj, AuthorizedUser.class);

        CurrentUser.currentUser = authorizedUser;

    }
}
