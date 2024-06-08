package com.nhnacademy.minidorray_gateway.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        Authentication auth = (Authentication) request.getUserPrincipal();

        if (auth != null) {
            // 로그를 찍거나, 추가적인 작업을 수행할 수 있습니다.
            System.out.println("User '" + auth.getName() + "' attempted to access the protected URL: " + request.getRequestURI());
        }

        // 리디렉션할 URL 설정
        response.sendRedirect(request.getContextPath() + "/access-denied");
    }
}
