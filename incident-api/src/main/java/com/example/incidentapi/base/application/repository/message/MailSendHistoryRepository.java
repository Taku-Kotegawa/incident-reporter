package com.example.incidentapi.base.application.repository.message;


import com.example.incidentapi.base.domain.model.message.MailSendHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailSendHistoryRepository extends JpaRepository<MailSendHistory, Long> {
}
