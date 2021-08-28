package com.example.petProject.changeRequestFeature.entity;

import com.example.petProject.changeRequestFeature.model.entityMarker.ChangeRequestEntityMarker;
import com.example.petProject.changeRequestFeature.model.enumTypes.ChangeRequestState;
import com.example.petProject.changeRequestFeature.model.enumTypes.OperationType;
import com.example.petProject.model.entity.BaseEntity;
import com.example.petProject.model.enumTypes.auth.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jsoniter.output.JsonStream;
import lombok.Data;
import lombok.ToString;
import netscape.javascript.JSObject;
import org.apache.tomcat.util.json.JSONParser;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@ToString
@Table(name = "change_request")
public class ChangeRequestEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by")
    private String createdBy;

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


    @OneToMany(
            targetEntity = ChangeRequestComment.class,
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
    private Set<ChangeRequestComment> changeRequestComments;


    public void setCurrentObjectState(ChangeRequestEntityMarker currentObjectState) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.currentObjectState = objectMapper.writeValueAsString(currentObjectState);
    }

    public void setNewObjectState(ChangeRequestEntityMarker newObjectState) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.newObjectState = objectMapper.writeValueAsString(newObjectState);
    }
}
