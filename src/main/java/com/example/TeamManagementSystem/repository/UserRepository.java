package com.example.TeamManagementSystem.repository;

import com.example.TeamManagementSystem.domain.entity.UserEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    void deleteById(@NonNull Long id);

    @Override
    @NonNull
    UserEntity getById(@NonNull Long id);
}
