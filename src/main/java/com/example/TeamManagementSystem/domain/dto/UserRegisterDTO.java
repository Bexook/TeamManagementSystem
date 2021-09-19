package com.example.TeamManagementSystem.domain.dto;

import com.example.TeamManagementSystem.domain.enumTypes.TeamMemberRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRegisterDTO {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("repeatPassword")
    private String repeatPassword;
    @JsonProperty("email")
    private String email;
    @JsonProperty("teamMemberRole")
    private TeamMemberRole teamMemberRole;

}
