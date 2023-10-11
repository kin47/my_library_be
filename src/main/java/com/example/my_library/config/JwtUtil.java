package com.example.my_library.config;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtUtil {
    private static final String TOKEN_PATTERN = "Bearer (.*)";

    public static String getAccessToken(String token) {
        Pattern pattern = Pattern.compile(TOKEN_PATTERN);
        Matcher matcher = pattern.matcher(token);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return token;
    }

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiry-min}")
    private long jwtExpiryMin;


    public String generateToken(Long userId, Map<String, Object> info) {

        long jwtExpiryMiniSec = jwtExpiryMin * 60 * 1000;
        Date iss = new Date();
        Date exp = new Date(iss.getTime() + jwtExpiryMiniSec);
//        info.put("authorities", authorityRoles);
        return Jwts.builder()
                .setClaims(info)
                .setSubject(String.valueOf(userId))
                .setIssuer("minhtq")
                .setIssuedAt(iss)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

    }


    public Claims getClaims(final String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (SignatureException ex) {
            throw new JwtException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new RuntimeException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new RuntimeException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("JWT claims string is empty.");
        }
    }
}