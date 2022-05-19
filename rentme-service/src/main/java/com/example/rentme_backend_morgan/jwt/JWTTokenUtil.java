package com.example.rentme_backend_morgan.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JWTTokenUtil {

    @Value("${jwt.maxExpiration}")
    private int expiration;

    @Value("${jwt.secret}")
    private String secret;

    // READ
    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //retrieve something from jwt token claims
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //retrieve username from jwt token claims
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token claims
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //retrieve roles from jwt token claims
    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        return (List<String>) getClaimFromToken(token, claims -> claims.get("roles"));
    }


    // GENERATION
    public String generateToken(Authentication authentication) {
        List<String> roles = authentication.getAuthorities().stream().map(a -> a.getAuthority().substring(5))
                .collect(Collectors.toList());

        return Jwts.builder().setClaims(new HashMap<String, Object>())
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String generateToken(String login, String password, List<String> roles) {
        return Jwts.builder().setClaims(new HashMap<String, Object>())
                .setSubject(login)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
