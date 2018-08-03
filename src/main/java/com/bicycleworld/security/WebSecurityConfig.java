package com.bicycleworld.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
//        httpSecurity.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
        httpSecurity.authorizeRequests().antMatchers("/message").hasRole("ADMIN").antMatchers("/productname").hasRole("USER").and().httpBasic();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(customAuthenticationProvider);
        //auth.inMemoryAuthentication().withUser("MILAN").password("PRASE").roles("ADMIN").and().withUser("PERA").password("PERIC").roles("CLEANER");
    }
}
