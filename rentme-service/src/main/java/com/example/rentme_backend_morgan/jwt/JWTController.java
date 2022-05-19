package com.example.rentme_backend_morgan.jwt;


import com.example.rentme_backend_morgan.security.services.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.rentme_backend_morgan.security.dto.RegisterDto;
import com.example.rentme_backend_morgan.security.services.ISecurityService;

import javax.validation.Valid;
import java.util.List;

import static com.example.rentme_backend_morgan.security.api.SecurityApi.*;


@RestController
@RequestMapping(JWT)
@RequiredArgsConstructor
@Validated
@Slf4j
public class JWTController {

    private final JWTTokenUtil jwtTokenUtil;
    private final ISecurityService service;

    @GetMapping(AUTHENTICATE)
    public synchronized ResponseEntity<?> createToken() {
        return makeResponse(
                jwtTokenUtil.generateToken(SecurityContextHolder.getContext().getAuthentication()));
    }

    @PostMapping(REGISTER)
    public synchronized ResponseEntity<?> registerAndCreateToken(@Valid @RequestBody RegisterDto dto) {
        service.addProfile(dto);
        return makeResponse(jwtTokenUtil.generateToken(
                dto.getLogin(),
                dto.getPassword(),
                List.of(dto.getRole())));
    }

    private ResponseEntity<?> makeResponse(String token) {
        return ResponseEntity.ok(new JWTResponse(
                token,
                jwtTokenUtil.getUsernameFromToken(token),
                jwtTokenUtil.getRolesFromToken(token),
                jwtTokenUtil.getExpirationDateFromToken(token)));
    }
}
