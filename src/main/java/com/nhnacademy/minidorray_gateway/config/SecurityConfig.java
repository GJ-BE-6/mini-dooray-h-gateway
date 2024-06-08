package com.nhnacademy.minidorray_gateway.config;


import com.nhnacademy.minidorray_gateway.domain.user.service.CustomUserDetailsService;
import com.nhnacademy.minidorray_gateway.handler.LoginFailedHandler;
import com.nhnacademy.minidorray_gateway.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailedHandler loginFailedHandler;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, LoginSuccessHandler loginSuccessHandler, LoginFailedHandler loginFailedHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailedHandler = loginFailedHandler;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin((formlogin) -> formlogin.loginPage("/auth").usernameParameter("id").passwordParameter("password").loginProcessingUrl("/auth").successHandler(loginSuccessHandler).failureHandler(loginFailedHandler));
        http.authorizeHttpRequests(requests -> requests.requestMatchers("/auth", "/account").permitAll()
                        .requestMatchers("/project/**").hasAuthority("ROLE_USER")
                        .anyRequest().permitAll()


        );
        http.userDetailsService(userDetailsService());
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
