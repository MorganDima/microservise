package com.example.rentme_backend_morgan.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String msg = (String) request.getAttribute("wrongToken");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.getWriter().println("Unauthenticated: " + (msg == null ? "Wrong login-password pair" : msg));
    }
}
