package com.example.petProject.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "active_tokens")
public class JWTTokenEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "jwt_token")
    private String jwtToken;


    public JWTTokenEntity(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
