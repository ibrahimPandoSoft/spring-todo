package com.pando.rest_todo.controller;

import com.pando.rest_todo.model.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    private JwtEncoder jwtEncoder;
    private AuthenticationManager authenticationManager;

    public AuthController(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(Authentication authentication) {
        return new JwtResponse(createToken(authentication));
    }

//    @PostMapping("/authenticate")
//    public String authenticate(@RequestBody LoginRequest loginRequest) {
//        try {
//            // Authenticate the user
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//            );
//
//            // Generate a JWT
//            Instant now = Instant.now();
//            String scope = authentication.getAuthorities().stream()
//                    .map(auth -> auth.getAuthority())
//                    .collect(Collectors.joining(" "));
//
//            JwtClaimsSet claims = JwtClaimsSet.builder()
//                    .issuer("self")
//                    .issuedAt(now)
//                    .expiresAt(now.plusSeconds(3600)) // 1 hour expiry
//                    .subject(authentication.getName())
//                    .claim("scope", scope)
//                    .build();
//
//            return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//        } catch (AuthenticationException ex) {
//            throw new RuntimeException("Invalid username or password");
//        }
//    }

    private String createToken(Authentication authentication) {
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 30))
                .subject(authentication.getName())
                .claim("scope", createScope(authentication))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }

    private String createScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(" "));
    }

}

record JwtResponse(String token) {}
