package com.example.TeamManagementSystem.changeRequestFeature.model.entity;

import com.example.TeamManagementSystem.model.entity.BaseEntity;
import com.example.TeamManagementSystem.model.enumTypes.auth.UserRole;
import com.example.TeamManagementSystem.changeRequestFeature.model.ChangeEntityEventListener;
import com.example.TeamManagementSystem.changeRequestFeature.model.entityMarker.ChangeRequestEntityMarker;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.ChangeRequestState;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.OperationType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@ToString
@Table(name = "change_request")
@EqualsAndHashCode(callSuper = true)
@Where(clause = " is_relevant = true ")
@EntityListeners(ChangeEntityEventListener.class)
@SQLDelete(sql = "UPDATE change_request SET is_relevant = 0 WHERE public.change_request.id= ? ", check = ResultCheckStyle.COUNT)
public class ChangeRequestEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "approver_role")
    private UserRole userRole;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state")
    private ChangeRequestState changeRequestState;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "operation_type")
    private OperationType operationType;
    @Column(name = "current_object_state")
    private String currentObjectState;

    @Column(name = "new_object_state")
    private String newObjectState;

    @Column(name = "object_type")
    private String objectType;

    @OneToMany(
            targetEntity = ChangeRequestCommentEntity.class,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    @JoinTable(name = "change_request_comments_ref",
            joinColumns = {
                    @JoinColumn(
                            name = "change_request_id",
                            referencedColumnName = "id",
                            table = "change_request",
                            foreignKey = @ForeignKey(name = "change_request_id_fk")
                    )},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "comments_id",
                            referencedColumnName = "id",
                            table = "change_request_comment",
                            foreignKey = @ForeignKey(name = "comment_id_fk")
                    )}
    )
    private Set<ChangeRequestCommentEntity> changeRequestCommentEntities;

    @Column(name = "is_relevant")
    private boolean isRelevant = true;

    @Column(name = "object_repo")
    private String objectRepo;

    public void setCurrentObjectState(ChangeRequestEntityMarker currentObjectState) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.currentObjectState = objectMapper.writeValueAsString(currentObjectState);
    }

    public void setNewObjectState(ChangeRequestEntityMarker newObjectState) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.newObjectState = objectMapper.writeValueAsString(newObjectState);
    }


    public void setNewObjectState(String newObjectState) {
        this.newObjectState = newObjectState;
    }
}