package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImp") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return  NoOpPasswordEncoder.getInstance();
//    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("u1")
////                .password("p1")
////                .authorities("ROLE_USER")
////                .and()
////                .withUser("u2")
////                .password("p2")
////                .authorities("ROLE_USER");
//
//    }
@Override
public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests().antMatchers("/login").anonymous()
            .antMatchers("/").hasAnyAuthority("ADMIN", "USER")
            .antMatchers("/code").hasAnyAuthority("ADMIN", "USER")
            .antMatchers("/chek").hasAnyAuthority("ADMIN", "USER")
            .antMatchers("/divrefresh").hasAnyAuthority("ADMIN", "USER")
            .antMatchers("/admin").hasAuthority("ADMIN")
            .anyRequest().authenticated().and().formLogin().successHandler(new LoginSuccessHandler());
    http.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login").and().csrf().disable();
}
}