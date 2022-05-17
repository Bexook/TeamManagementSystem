package com.example.TeamManagementSystem.service.model.impl;


import com.tms.common.mapper.OrikaBeanMapper;
import com.example.TeamManagementSystem.repository.UserMessagesRepository;
import com.example.TeamManagementSystem.service.UserMessagesService;
import com.tms.common.util.AuthorizationUtils;
import com.tms.common.domain.UserMessagesEntity;
import com.tms.common.domain.dto.UserMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessagesServiceImpl implements UserMessagesService {

    @Autowired
    private UserMessagesRepository userMessagesRepository;
    @Autowired
    private OrikaBeanMapper mapper;


    @Override
    public List<UserMessageDTO> getChats() {
        return mapper.mapAsList(userMessagesRepository.findBySenderIdAndOrderBySendingTimeAsc(AuthorizationUtils.getCurrentAuthenticatedUserId()), UserMessageDTO.class);
    }

    @Override
    public List<UserMessageDTO> deleteChat(Long receiverId) {
        return null;
    }

    @Override
    public List<UserMessageDTO> getChatByReceiverId(Long receiverId) {
        return mapper.mapAsList(userMessagesRepository.findByReceiverIdAndOrderBySendingTimeAsc(AuthorizationUtils.getCurrentAuthenticatedUserId()), UserMessageDTO.class);
    }

    @Override
    public void deleteMessageById(Long messageId) {

    }

    @Override
    public void readMessage(final Long messageId) {
        userMessagesRepository.readById(messageId);
    }

    @Override
    public void sendMessage(UserMessageDTO userMessageDTO) {
        userMessagesRepository.save(mapper.map(userMessageDTO, UserMessagesEntity.class));
    }
}
