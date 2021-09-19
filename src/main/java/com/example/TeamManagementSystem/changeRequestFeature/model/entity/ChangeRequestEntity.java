package com.example.TeamManagementSystem.changeRequestFeature.model.entity;

import com.example.TeamManagementSystem.domain.entity.BaseEntity;
import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;
import com.example.TeamManagementSystem.changeRequestFeature.model.ChangeEntityEventListener;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.ChangeRequestState;
import com.example.TeamManagementSystem.changeRequestFeature.model.enumTypes.OperationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
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
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
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
                            name = "comment_id",
                            referencedColumnName = "id",
                            table = "change_request_comment",
                            foreignKey = @ForeignKey(name = "comment_id_fk")
                    )}
    )
    @Fetch(FetchMode.JOIN)
    private Set<ChangeRequestCommentEntity> changeRequestCommentEntities;

    @Column(name = "is_relevant")
    private boolean relevant = true;

    @Column(name = "object_repo")
    private String objectRepo;

}
