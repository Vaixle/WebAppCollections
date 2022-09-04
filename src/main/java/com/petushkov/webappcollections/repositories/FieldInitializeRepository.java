package com.petushkov.webappcollections.repositories;

import com.petushkov.webappcollections.models.FieldInitialize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldInitializeRepository extends JpaRepository<FieldInitialize, Long> {
}
