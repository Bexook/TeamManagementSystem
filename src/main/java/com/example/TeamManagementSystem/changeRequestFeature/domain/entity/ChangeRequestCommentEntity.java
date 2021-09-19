package com.example.TeamManagementSystem.changeRequestFeature.domain.entity;

import com.example.TeamManagementSystem.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
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

    @Column(name = "is_relevant")
    private boolean relevant = true;


}
