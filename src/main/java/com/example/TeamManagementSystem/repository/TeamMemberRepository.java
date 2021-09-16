package com.example.TeamManagementSystem.repository;

import com.example.TeamManagementSystem.model.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.model.entity.TeamMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMemberEntity, Long> {


    TeamMemberEntity getById(Long id);

    @Query(nativeQuery = true, name = "findDTOById")
    TeamMemberDTO findByDTOId(@Param("id") Long id);


    @Query(nativeQuery = true, name = "findDTOByUserId")
    TeamMemberDTO findByDTOUserId(@Param("id") Long id);

    void deleteById(Long id);

}