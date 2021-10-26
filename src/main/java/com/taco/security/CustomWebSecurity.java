package com.taco.security;

import com.taco.repository.UserRepository;
import com.taco.security.jwtFilter.JwtAuthenticationFilter;
import com.taco.security.jwtFilter.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CustomWebSecurity extends WebSecurityConfigurerAdapter {
    private UserRepository userRepository;

    private UserDetailsServiceImpl userDetailsServiceImpl;

    public CustomWebSecurity(UserRepository userRepository, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userRepository = userRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    // override configurer method with AuthenticationManagerBuilder and
    //configure the authentication with db
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //ktu jemi duke  integruar userat qe jan ne db me spring security
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    //override configurer method with HttpSecurity and configurer the jwt filters


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))
                .authorizeRequests()
        .antMatchers("/users").authenticated()
        .antMatchers("/orders").authenticated();

    }

    //authentication with db provider
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(this.userDetailsServiceImpl);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
