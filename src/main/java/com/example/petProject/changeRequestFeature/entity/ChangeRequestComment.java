package com.example.petProject.changeRequestFeature.entity;

import com.example.petProject.model.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "change_request_comment")
public class ChangeRequestComment extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "comment_by")
    private String commentBy;

    @ManyToOne
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
    private ChangeRequestEntity changeRequestEntity;

}
