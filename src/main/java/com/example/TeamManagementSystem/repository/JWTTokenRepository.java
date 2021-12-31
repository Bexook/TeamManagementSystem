package com.example.TeamManagementSystem.repository;


import com.tms.dao.tmsdao.domain.JWTTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JWTTokenRepository extends JpaRepository<JWTTokenEntity, Long> {


    JWTTokenEntity findByJwtToken(String token);

    void deleteByJwtToken(String token);


}
