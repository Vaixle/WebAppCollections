package com.petushkov.webappcollections.repositories;

import com.petushkov.webappcollections.models.ERole;
import com.petushkov.webappcollections.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
