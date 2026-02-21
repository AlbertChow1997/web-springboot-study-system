package com.albert.interceptor;

import com.albert.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1. Get request path
        String requestPath = request.getRequestURI();

        //2. Judge if it is login request
        if (requestPath.contains("/login")){
            log.info("Login request, skip token filter");
            return true;
        }

        //3. Get token from request header
        String token = request.getHeader("token");

        //4. Judge if token exist, if not, return error (401).
        if (token == null || token.isEmpty()){
            log.error("token is empty, return 401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5. If token exist, judge if it is valid. If not, return error (401).
        try{
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.error("token is invalid, return 401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //6. If token is valid, continue to process the request.
        log.info("token is valid, continue to process the request");
        return true;
    }
}
