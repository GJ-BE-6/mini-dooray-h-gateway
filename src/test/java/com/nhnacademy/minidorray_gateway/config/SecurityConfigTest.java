package com.nhnacademy.minidorray_gateway.config;

import com.nhnacademy.minidorray_gateway.domain.user.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginPageAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/auth"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testLoginWithValidCredentials() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login/process")
                        .param("userId", "testUser")
                        .param("password", "testPassword")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testLoginWithInvalidCredentials() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login/process")
                        .param("userId", "invalidUser")
                        .param("password", "invalidPassword")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/auth?error=true"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/logout"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

    @Test
    public void testAccessToSecuredPageWithoutAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void testAccessToSecuredPageWithAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home")
                        .with(SecurityMockMvcRequestPostProcessors.user("testUser").password("testPassword")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}

