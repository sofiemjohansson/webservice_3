package com.example.reddit.config;

import com.example.reddit.auth.JwtAuthEntryPoint;
import com.example.reddit.auth.JwtFilter;
import com.example.reddit.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final String[] unsecuredEndpoints = new String[] {"/api/auth/**", "/api/guest/**"};

    private final AuthService authService;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtFilter jwtFilter;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(AuthService authService, JwtAuthEntryPoint jwtAuthEntryPoint, JwtFilter jwtFilter, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(unsecuredEndpoints).permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}
