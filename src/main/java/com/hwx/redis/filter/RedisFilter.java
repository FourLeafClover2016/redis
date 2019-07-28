package com.hwx.redis.filter;

import com.hwx.redis.util.CookisUtils;
import com.hwx.redis.util.RedisPoolUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: Huawei Xie
 * @date: 2019/7/27
 */
@Component
@WebFilter(filterName = "redisFilter", urlPatterns="/*")
public class RedisFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getRequestURI().contains("login")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String sessionId = CookisUtils.readLoginToken(request);
        if (null == sessionId) {
            return;
        }
        if (null != sessionId){
            Object redisCookie = RedisPoolUtil.get(sessionId);
             if (null != redisCookie) {
                 RedisPoolUtil.expire(sessionId, 30 * 60);
                 filterChain.doFilter(servletRequest, servletResponse);
                 return;
             }

        }
    }

    @Override
    public void destroy() {

    }
}
