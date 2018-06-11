/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.config;

import com.apple.flagpicker.filters.HeaderTokenAuthFilter;
import com.apple.flagpicker.providers.CustomAuthenticationProvider;
import com.apple.flagpicker.util.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Shree
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class FlagPickerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FlagPickerProperties properties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(new HeaderTokenAuthFilter(new CustomAuthenticationProvider(), properties), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/**")
                .authenticated();
    }
    
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(new BasicPasswordEncryptor());
    }
}
