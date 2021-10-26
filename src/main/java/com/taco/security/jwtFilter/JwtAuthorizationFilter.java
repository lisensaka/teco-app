package com.taco.security.jwtFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.taco.models.User;
import com.taco.repository.UserRepository;
import com.taco.security.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//kjo klase do te gjeneroj autorizimet qe ka ky user i loguar
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository ;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //ktu marrim authorization headerin nga kerkesa e ardhur
        String authorizationHeader = request.getHeader(JwtProperties.HEADER_STRING);
        if (authorizationHeader == null || !authorizationHeader.startsWith(JwtProperties.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);

    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        //marrim tokenin nga kerkesa
        String token = request.getHeader(JwtProperties.HEADER_STRING);
        if (token != null) {
            //nese tokenin nuk eshte null, provojme ta dekriptojme qe te shohim nese userat kan leje te ken access per te par info
            // kte e bejm me ane te libraris se JWT, me ane te te njetjit algoritem qe eshte ber encryptimi dhe me ane te secret qe venodosem per encode tokenin
            String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    //ktu bejm verifikim e tokenit duket i hequr prefixin e vendosur
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    // dhe marrim usernamein nga tokeni
                    .getSubject();

            if (username != null) {
                //kontrollojme ne db nese ky username egziston
                User user = userRepository.findByUsername(username);

                // me pas ndertojme nje object te tipit UserDetailsImpl per kte username
                UserDetailsImpl userDetails = new UserDetailsImpl(user);
                //ndertojme nje token te tipit -> UsernamePasswordAuthenticationToken  nga ky userDetails
                //UsernamePasswordAuthenticationToken eshte i perber nga username, dhe permissionat qe ka ky ne app
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                return authenticationToken;
            }
        }
        return null;
    }
}
