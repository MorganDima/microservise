package com.example.rentme_backend_morgan.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import com.example.rentme_backend_morgan.security.api.AccountingResponses;
import com.example.rentme_backend_morgan.security.entities.Account;
import com.example.rentme_backend_morgan.security.repo.AccountRepo;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTRevokedFilter extends OncePerRequestFilter {

    private final AccountRepo mongoRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Authentication context = SecurityContextHolder.getContext().getAuthentication();
        if (context != null) {
            try {
                String login = context.getName();
                Account account = mongoRepository.findById(login).orElse(null);
                if (account.isRevoked())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, AccountingResponses.WRONG_USERNAME);
            } catch (Exception e) {
                request.setAttribute("revoked", e.getMessage());
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }
        filterChain.doFilter(request, response);
    }
}
