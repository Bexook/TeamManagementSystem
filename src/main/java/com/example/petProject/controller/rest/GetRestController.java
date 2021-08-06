package com.example.petProject.controller.rest;

import com.example.petProject.model.entity.UserEntity;
import com.example.petProject.service.mail.MailSenderService;
import com.example.petProject.service.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GetRestController {


    @Autowired
    private UserService userService;

    @Autowired
    private MailSenderService mailSenderService;

    @PreAuthorize("@userAccessValidation.hasRole('ADMIN')")
    @GetMapping("/")
    public String get() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
        return "HELLO";
    }

//    @PreAuthorize("@userAccessValidation.hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserEntity>> getAll() {
        System.out.println(userService.findAll(true));
        return ResponseEntity.ok(userService.findAll(true));
    }


    @PreAuthorize("@userAccessValidation.hasAuthority('DELETE')")
    @DeleteMapping("/delete/{userId}")
    public void deleteSpecific(@PathVariable Long userId) {
        userService.deleteById(userService.getUserById(userId).getId());
    }


    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/sendMail")
    public void senMail() throws MessagingException {
        mailSenderService.sendResetPasswordEmail();
    }

}
