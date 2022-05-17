package com.example.TeamManagementSystem.repository;


import com.tms.common.domain.TeamMemberEntity;
import com.tms.common.domain.dto.TeamMemberDTO;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMemberEntity, Long> {


    @NonNull TeamMemberEntity getById(Long id);

    @Query(nativeQuery = true, name = "findDTOById")
    TeamMemberDTO findByDTOId(@Param("id") Long id);


    @Query(nativeQuery = true, name = "findDTOByUserId")
    TeamMemberDTO findByDTOUserId(@Param("id") Long id);

    void deleteById(Long id);

}
