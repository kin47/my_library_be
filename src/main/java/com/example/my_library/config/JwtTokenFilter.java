package com.example.my_library.config;

import com.example.my_library.repo.UserRepo;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@Log4j2
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get authorization header and validate
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        // validate token
        // Get jwt token and validate
        token = JwtUtil.getAccessToken(token);
        try {
            Claims claims = jwtUtil.getClaims(token);
            Long userId = Long.parseLong(String.valueOf(claims.get("user_id")));
            String phone = (String) claims.get("phone");
            String email = (String) claims.get("email");
//            userRepo.findByUsername(u)
        } catch (Exception e) {
            log.info("error {}", e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        }
}
