//package com.nhnacademy.minidorray_gateway.handler;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//public class custom {
//
//    @Slf4j
//    @Component
//    public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//
//
//        @Autowired
//        private LoginErrorService loginErrorService;
//
//        @Override
//        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//                                            AuthenticationException exception) throws IOException, ServletException {
//            String userName = request.getParameter("username");
//            log.info("Login failure Id : {} ", userName);
//            loginErrorService.addCounter(userName);
//
//            if (exception.getCause() instanceof NotFindMemberException) {
//                request.getSession().setAttribute("message", "존재하지 않는 아이디 입니다.");
//                response.sendRedirect("/error1");
//                return;
//            }
//
//            super.onAuthenticationFailure(request, response, exception);
//        }
//
//    }
//
//    @Slf4j
//    @Component
//    public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//        @Autowired
//        private LoginErrorService loginErrorService;
//
//
//        @Override
//        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                            Authentication authentication) throws IOException, ServletException {
//            loginErrorService.deleteCounter(authentication.getName());
//            log.info("Login successful");
//            response.sendRedirect("/");
//
//        }
//    }
//
//}
