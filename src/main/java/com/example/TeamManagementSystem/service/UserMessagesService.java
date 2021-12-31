package com.example.TeamManagementSystem.service;

import com.tms.dao.tmsdao.domain.dto.UserMessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMessagesService {

    List<UserMessageDTO> getChats();

    List<UserMessageDTO> deleteChat(final Long receiverId);

    List<UserMessageDTO> getChatByReceiverId(final Long receiverId);

    void deleteMessageById(final Long messageId);

    void readMessage(final Long messageId);

    void sendMessage(final UserMessageDTO userMessageDTO);


}
