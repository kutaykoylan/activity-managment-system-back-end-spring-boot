package com.tubitak.activitybackend.common.config;


import com.tubitak.activitybackend.services.userservice.common.service.CustomUserDetailsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/create").permitAll()
                .antMatchers("/activity/allActivities").permitAll()
                .antMatchers("/activity/allActivities/**").permitAll()
                .antMatchers("/mail/send").permitAll()
                .antMatchers("/QR").permitAll()
                .antMatchers("/activity/pages/**").permitAll()
                .antMatchers("/activity/numberOfPages").permitAll()
                .antMatchers("/usersActivities/users").permitAll()
                .antMatchers("/usersActivities/activities").hasAuthority("USER")
                .antMatchers("/usersActivities/delete").hasAuthority("USER")
                .antMatchers("/usersActivities/create").hasAuthority("USER")
                .antMatchers("/activity/update").hasAuthority("ADMIN")
                .antMatchers("/activity/delete").hasAuthority("ADMIN")
                .antMatchers("/activity/add").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable();

    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsManager);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

}