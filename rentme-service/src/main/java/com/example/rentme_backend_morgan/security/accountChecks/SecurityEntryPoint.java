package com.example.rentme_backend_morgan.security.accountChecks;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.*;
import java.io.IOException;

@Component
public class SecurityEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException authException) throws IOException
	{
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().print("HTTP Status 401 : Basic authentication failed");
	}
}