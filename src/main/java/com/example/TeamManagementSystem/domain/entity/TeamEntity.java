package com.example.TeamManagementSystem.domain.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "project_name")
    private String projectName;
    @OneToMany(targetEntity = TeamMemberEntity.class, fetch = FetchType.LAZY)
    @JoinTable(name = "team_member_ref_team",
            inverseJoinColumns = {@JoinColumn(name = "team_member_id", referencedColumnName = "id")},
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")})
    private List<TeamMemberEntity> teamMembers;

}
