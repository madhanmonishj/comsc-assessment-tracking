package com.assessment.comsc.config;

import com.assessment.comsc.controller.CustomAuthenticationFailureHandler;
import com.assessment.comsc.entity.UserInfo;
import com.assessment.comsc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserInfoService userInfoService;

    //Get the mail and query the user information, then compare both.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mail -> {

            UserInfo userInfoByMail = userInfoService.getUserInfoByMail(mail);

            if (Objects.nonNull(userInfoByMail)) {
                return org.springframework.security.core.userdetails.User
                        .withUsername(mail)
                        .password(userInfoByMail.getPassword())
                        .roles("USER")
                        .build();
            } else {
                throw new IllegalArgumentException("User not found");
            }
        }).passwordEncoder(passwordEncoder());
    }

    //Permission configuration
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
//                .antMatchers("/availabilityForm/saveData").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/loginPage")
                // This will be executed after successful login.
                .successHandler(new MyAuthenticationSuccessHandler(userInfoService))
                .failureHandler(new CustomAuthenticationFailureHandler())
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable(); // Disable CSRF protection
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Password encryption code block, which needs to be encrypted and stored in the database
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // When the user registers, encrypt the password and save it to the database.
        String rawPassword = "123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
