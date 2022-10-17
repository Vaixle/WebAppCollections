package com.petushkov.webappcollections.services.impl;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.petushkov.webappcollections.exceptions.TokenRefreshException;
import com.petushkov.webappcollections.models.RefreshToken;
import com.petushkov.webappcollections.repositories.RefreshTokenRepository;
import com.petushkov.webappcollections.repositories.UserRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@RequiredArgsConstructor
public class RefreshTokenServiceImpl {

    @Value("${collections.app.jwtRefreshExpirationMs}")
    Long refreshTokenDurationMs;

    final RefreshTokenRepository refreshTokenRepository;

    final UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = refreshTokenRepository.findById(userId).orElse(new RefreshToken());
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteAllByUserId(Long userId) {
        return refreshTokenRepository.deleteAllByUser(userRepository.findById(userId).get());
    }
}
