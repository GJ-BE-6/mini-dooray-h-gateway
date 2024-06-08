//package com.nhnacademy.minidorray_gateway.config;
//
//import com.nhnacademy.minidorray_gateway.domain.user.service.CustomUserDetailsService;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class secucon {
//
//        private final CustomUserDetailsService customUserDetailsService;
//        private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//        private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
//
//
//        public SecurityConfig(CustomUserDetailsService customUserDetailsService
//                , CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler
//                , CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
//            this.customUserDetailsService = customUserDetailsService;
//            this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
//            this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
//        }
//
//        @Bean
//        public UserDetailsService userDetailsService() {
//            return customUserDetailsService;
//        }
//
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http.csrf(AbstractHttpConfigurer::disable);
//            http.formLogin((formLogin) ->
//                    formLogin.loginPage("/login")
//                            .usernameParameter("username")
//                            .passwordParameter("password")
//                            .defaultSuccessUrl("/")
//                            .failureUrl("/login?error")
//                            .loginProcessingUrl("/login/process")
//                            .successHandler(customAuthenticationSuccessHandler)
//                            .failureHandler(customAuthenticationFailureHandler)
//
//            );
//
//
//            http.authorizeHttpRequests(authorizeRequests ->
//                    authorizeRequests
//                            .requestMatchers("/project/**").authenticated()
//                            .anyRequest().permitAll()
//            );
//
//            http.exceptionHandling(exceptionHandling ->
//                    exceptionHandling
//                            .accessDeniedHandler((request, response, accessDeniedException) -> {
//                                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                                response.setContentType("application/json; charset=utf-8");
//                                response.getWriter().write("접근이 제한 되었습니다.");
//                            })
//            );
//
//            http.logout((logout)->logout.deleteCookies("A-COOKIE", "B-COOKIE")
//                    .invalidateHttpSession(true)
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/"));
//
//            http.userDetailsService(userDetailsService());
//
//            return http.build();
//        }
//    }
//}
