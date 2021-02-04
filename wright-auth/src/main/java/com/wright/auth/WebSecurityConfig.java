package com.wright.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 	 允许匿名访问所有接口 主要是 oauth 接口
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//    	 http.formLogin()
//         .and()
//         .authorizeRequests()
//         .antMatchers("/**").permitAll();
    	
    	// 默认支持./login实现authorization_code认证
        http
            .formLogin().loginPage("/login").loginProcessingUrl("/authentication/form")
            .and()
            .authorizeRequests()
            .antMatchers("/login","/resources/**", "/static/**", "/oauth/**").permitAll()
            .anyRequest() // 任何请求
            .authenticated()// 都需要身份认证
          //  .and()
         //   .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessHandler(customLogoutSuccessHandler).permitAll()
            .and()
            .csrf().disable();
    }
}
