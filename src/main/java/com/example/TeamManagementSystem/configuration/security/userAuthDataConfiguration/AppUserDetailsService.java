package com.example.TeamManagementSystem.configuration.security.userAuthDataConfiguration;

import com.example.TeamManagementSystem.repository.UserRepository;
import com.tms.dao.tmsdao.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new AppUserDetails(findUserByEmail(s));
    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
