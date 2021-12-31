package com.example.TeamManagementSystem.repository;

import com.tms.dao.tmsdao.domain.UserMessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessagesRepository extends JpaRepository<UserMessagesEntity, Long> {

    void deleteAllByReceiverId(final long receiverId);

    void readById(final long messageId);

    void deleteById(final long messageId);

    @Query(name = "findByReceiverIdAndOrderBySendingTime", nativeQuery = true)
    List<UserMessagesEntity> findByReceiverIdAndOrderBySendingTimeAsc(final long receiverId);

    @Query(name = "findBySenderIdAndOrderBySendingTime", nativeQuery = true)
    List<UserMessagesEntity> findBySenderIdAndOrderBySendingTimeAsc(final long senderId);

}
