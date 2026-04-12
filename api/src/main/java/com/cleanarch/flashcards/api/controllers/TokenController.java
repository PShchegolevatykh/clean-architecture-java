package com.cleanarch.flashcards.api.controllers;

import com.cleanarch.flashcards.api.models.TokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class TokenController {

    private final JwtEncoder encoder;

    @Value("${app.security.client-id}")
    private String expectedClientId;

    @Value("${app.security.client-secret}")
    private String expectedClientSecret;

    public TokenController(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody TokenRequest request) {
        if (!expectedClientId.equals(request.clientId()) || !expectedClientSecret.equals(request.clientSecret())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "invalid_client"));
        }

        Instant now = Instant.now();
        long expiry = 3600L; // 1 hour

        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(request.clientId())
                .claim("scope", "api:read api:write")
                .build();

        String token = this.encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

        return ResponseEntity.ok(Map.of(
                "access_token", token,
                "token_type", "Bearer",
                "expires_in", expiry
        ));
    }
}
