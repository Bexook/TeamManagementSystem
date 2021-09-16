package com.example.TeamManagementSystem.changeRequestFeature;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@FunctionalInterface
public interface SearchFunction<R extends JpaRepository<?, ? extends Number>, O> {
    Page<O> apply(Object o, R repo, Pageable pageable);
}