package com.devsuperior.bds04.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String[] PUBLIC_ENDPOINT = {
            "/oauth/token",
            "/h2-console/**"
    };
    
    private static final String[] GET_PUBLIC_ENDPOINTS = {
            "/cities",
            "/events"
    };
    
    private static final String CLIENT_OR_ADMIN_ENDPOINTS = "/events/**";
    
    @Autowired
    private Environment env;
    
    @Autowired
    private JwtTokenStore tokenStore;
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (Arrays.asList(env.getActiveProfiles()).contains("test"))
            http.headers().frameOptions().disable();
        
        http.authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.GET, GET_PUBLIC_ENDPOINTS).permitAll()
                .antMatchers(CLIENT_OR_ADMIN_ENDPOINTS).hasAnyRole("ADMIN", "CLIENT")
                .anyRequest().hasRole("ADMIN");
    }
}
