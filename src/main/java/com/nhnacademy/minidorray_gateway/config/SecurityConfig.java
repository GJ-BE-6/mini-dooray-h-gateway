//package com.nhnacademy.minidorray_gateway.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SecurityConfig {
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.formLogin((formlogin) -> formlogin.loginPage("/login").usernameParameter("id").passwordParameter("password").loginProcessingUrl("/login"));
//        http.authorizeHttpRequests(requests -> requests.requestMatchers("/auth/login","/login", "/members", "/members/**").permitAll()
//                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
////                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/private-project/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEMBER")
////                        .requestMatchers("/private-project/**").hasAnyRole("ADMIN", "MEMBER") << role에서 ROLE 붙혀줌
//                        .requestMatchers("/public-project/**").permitAll()
//                        .anyRequest().authenticated()
//        );
//
//
//        return http.build();
//    }
//
//
//}
