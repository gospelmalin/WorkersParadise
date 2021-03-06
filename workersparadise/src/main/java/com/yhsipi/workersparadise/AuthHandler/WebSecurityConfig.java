package com.yhsipi.workersparadise.AuthHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService CustomUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private DataSource dataSource;




    //private final String USERS_QUERY = "select username, password from users where username=?";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(CustomUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);



    }
/*
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(USERS_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions().sameOrigin()
                .and()

                //Regler vi sätter. Kan vara permitall, denyall eller baserat på hasrole
                .authorizeRequests()
                .antMatchers("/resources/**", "/webjars/**", "/assets/**").permitAll()
                
                // URLs
                .antMatchers("/", "/search/**", "/profile/**", "/profil/**", "/account/register", "/login", "upload/imageupload/new", "upload/imageupload/delete").permitAll()
                
                // Webapp
                .antMatchers("/bootstrap/**","/css/**","/fontawesome-free/**", "/img/**", "/jquery/**",
                		"/jquery-easing/**","/custom.js", "/resume.js", "/resume.min.js","/resume.min.css",
                		"/bootstrap/**").permitAll()
                
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()


                //Form hanteringen
                .formLogin()
                .loginPage("/account/login")
                .defaultSuccessUrl("/account/dashboard")
                .failureUrl("/account/login?error")
                .permitAll()
                .and()

                //Vad händer vid logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/account/logout"))
                .logoutSuccessUrl("/account/login?logout")
                .deleteCookies("my-remember-me-cookie")
                .permitAll()
                .and()

                //Sätter en remember me token, OM vi vill det
                .rememberMe()
                //.key("my-secure-key")
                .rememberMeCookieName("my-remember-me-cookie")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60)
                .and()
                .exceptionHandling()
        ;
        http.httpBasic().and().cors().and().csrf().disable();

    }


    //

    //Kommer användas för att kunna hålla en remember me token för användare. TODO: Detta behöver testas och implementeras bättre
    PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
}