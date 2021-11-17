package com.tfworkers.PDSISystem.Security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // TODO Auto-generated method stub
//        http.authorizeRequests()
//                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf().disable();
//    }
//}