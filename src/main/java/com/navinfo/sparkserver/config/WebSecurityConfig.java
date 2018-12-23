///**************************************
// * Copyright (C), Navinfo
// * Package: com.navinfo.sparkserver.config
// * Author: wulongyue06158
// * Date: Created in 2018/12/21 11:23
// **************************************/
//package com.navinfo.sparkserver.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
///*************************************
// * Class Name: WebSecurityConfig
// * Description:〈websocket点对点〉
// * @author wulongyue
// * @create 2018/12/21
// * @since 1.0.0
// * 消息由谁发送，由谁接收
// ************************************/
//@Configuration
//@CrossOrigin("*")
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "login").permitAll() //设置Spring Security对/和"/login"路径不拦截
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login")//设置Spring Security的登陆页面访问路径为login
////                .defaultSuccessUrl("/chat") //登陆成功后转向/chat路径
//                .permitAll().and().logout()
//                .permitAll();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //配置两个用户,角色是user
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("james").password(new BCryptPasswordEncoder().encode("james")).roles("user")
//                .and()
//                .withUser("curry").password(new BCryptPasswordEncoder().encode("curry")).roles("user");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 设置Spring Secutiry不拦截/resources/static/目录下的静态资源
//        web.ignoring().antMatchers("/resources/static/**");
//    }
//
//}
