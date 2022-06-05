package com.example.TeamManagementSystem.service.user.impl;

import com.example.TeamManagementSystem.domain.dto.UserDTO;
import com.example.TeamManagementSystem.domain.dto.UserRegisterDTO;
import com.example.TeamManagementSystem.domain.entity.UserEntity;
import com.example.TeamManagementSystem.domain.enumTypes.auth.UserRole;
import com.example.TeamManagementSystem.service.user.AccountDetailsService;
import com.example.TeamManagementSystem.service.user.UserService;
import com.example.TeamManagementSystem.domain.dto.TeamMemberDTO;
import com.example.TeamManagementSystem.domain.entity.TeamMemberEntity;
import com.example.TeamManagementSystem.service.team.TeamMemberService;
import com.example.TeamManagementSystem.util.AuthorizationUtils;
import javassist.tools.web.BadHttpRequest;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.SystemException;
import java.util.List;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public TeamMemberDTO getAccountDetailsForSpecificAcc(String userEmail) {
        return teamMemberService.findByUserId(userService.findByEmail(userEmail).getId());
    }

    @Override
    public List<TeamMemberDTO> getAccountDetails() {
        return teamMemberService.findAllTeamMembers();
    }

    @Override
    public UserDTO getUserDetails() {
        return userService.findByEmail(AuthorizationUtils.getCurrentUsername());
    }

    @Override
    @SneakyThrows
    public UserDTO updateUserDetails(UserDTO userDTO) {
        if (AuthorizationUtils.getCurrentUsername().equals(userDTO.getEmail())) {
            userService.update(userDTO);
            return userService.findByEmail(userDTO.getEmail());
        }
        throw new SystemException(403);
    }


    @SneakyThrows
    @Override
    public void createNewAccount(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getRepeatPassword())) {
            throw new BadHttpRequest();
        }

        if (Strings.isBlank(userRegisterDTO.getEmail())) {
            throw new BadHttpRequest();
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRegisterDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userEntity.setUserRole(UserRole.USER);
        TeamMemberEntity teamMember = new TeamMemberEntity();
        teamMember.setName(userRegisterDTO.getUsername());
        teamMember.setMemberRole(userRegisterDTO.getTeamMemberRole());
        teamMember.setUser(userEntity);
        teamMemberService.registerTeamMember(teamMember);
    }
}
