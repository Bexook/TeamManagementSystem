package com.example.TeamManagementSystem.repository;

import com.example.TeamManagementSystem.model.entity.JWTTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JWTTokenRepository extends JpaRepository<JWTTokenEntity, Long> {


    JWTTokenEntity findByJwtToken(String token);

    void deleteByJwtToken(String token);


}
