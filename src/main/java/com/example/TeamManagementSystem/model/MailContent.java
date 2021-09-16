package com.example.TeamManagementSystem.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.util.Pair;

@Data
@Builder
public class MailContent {

    private String subject;
    private Pair<String, String> body;
}
