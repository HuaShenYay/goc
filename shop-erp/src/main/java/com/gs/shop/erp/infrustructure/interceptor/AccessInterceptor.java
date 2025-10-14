package com.gs.shop.erp.infrustructure.interceptor;

import com.gs.shop.erp.infrustructure.auth.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 *
 * @author Pursuer
 * @version 1.0
 * @date 2023/11/15
 */
@Component
@RequiredArgsConstructor
public class AccessInterceptor implements HandlerInterceptor {

    private final SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        sessionService.checkLogin();

        return true;
    }
}