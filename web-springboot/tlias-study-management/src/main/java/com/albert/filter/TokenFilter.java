package com.albert.filter;

import com.albert.utils.CurrentHolder;
import com.albert.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. Get request path
        String requestPath = request.getRequestURI();

        //2. Judge if it is login request
        if (requestPath.contains("/login")){
            log.info("Login request, skip token filter");
            filterChain.doFilter(request, response);
            return;
        }

        //3. Get token from request header
        String token = request.getHeader("token");

        //4. Judge if token exist, if not, return error (401).
        if (token == null || token.isEmpty()){
            log.error("token is empty, return 401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5. If token exist, judge if it is valid. If not, return error (401).
        try{
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("Current empId:{}", empId);
        } catch (Exception e) {
            log.error("token is invalid, return 401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //6. If token is valid, continue to process the request.
        log.info("token is valid, continue to process the request");
        filterChain.doFilter(request, response);

        //7. Release the resource in CurrentHolder
        CurrentHolder.remove();
    }

}
