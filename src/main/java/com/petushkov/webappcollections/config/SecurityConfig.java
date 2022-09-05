package com.petushkov.webappcollections.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;



/**
 * Configuration for spring security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  {



    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    /**
     * @return error handler created
     */
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }


    /**
     * @return password encoder to crypt password
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * @result AuthenticationManager to check user credentials
     */
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

    }


    /**
     * Http security configuration
     *
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable() // disable Cross-Origin Resource Sharing
                .csrf().disable() //disable Cross-Site Request Forgery
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") //access for admin
                .antMatchers(HttpMethod.GET, "/api/**").permitAll() //access for all, only get request
                .antMatchers(HttpMethod.POST,"/api/app-collections/users/sign-up").permitAll() //access for all, only post request
                .antMatchers("/api/**").hasRole("USER") //access for user, all requests
                .antMatchers("/**").permitAll() //other requests access for all
                .and()
                .formLogin()
                .loginPage("/login") //login page view
                .failureHandler(customAuthenticationFailureHandler()); //custom error handler

        return http.build();

    }


}
