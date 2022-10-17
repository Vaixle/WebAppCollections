package com.petushkov.webappcollections.repositories;

import com.petushkov.webappcollections.models.RefreshToken;
import com.petushkov.webappcollections.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    int deleteAllByUser(User user);
}
