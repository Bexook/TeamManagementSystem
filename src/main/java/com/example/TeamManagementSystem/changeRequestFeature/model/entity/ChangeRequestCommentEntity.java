package com.example.TeamManagementSystem.changeRequestFeature.model.entity;

import com.example.TeamManagementSystem.model.entity.BaseEntity;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "change_request_comment")
@Where(clause = " is_relevant = true ")
@SQLDelete(sql = "UPDATE change_request SET is_relevant = 0 WHERE public.change_request_comment.id= ? ", check = ResultCheckStyle.COUNT)
public class ChangeRequestCommentEntity extends BaseEntity {

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

    @Column(name = "is_relevant")
    private boolean is_relevant = true;

}
