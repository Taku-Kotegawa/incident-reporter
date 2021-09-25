package com.example.incidentapi.incident.application.service;


import com.example.incidentapi.base.application.service.AbstractNodeService;
import com.example.incidentapi.base.domain.model.message.MailSendHistory;
import com.example.incidentapi.incident.application.repository.IncidentRepository;
import com.example.incidentapi.incident.domain.model.Incident;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class IncidentServiceImpl extends AbstractNodeService<Incident, Long> implements IncidentService {

    private final IncidentRepository incidentRepository;


    @Override
    protected JpaRepository<Incident, Long> getRepository() {
        return incidentRepository;
    }

    @Override
    public void send(Incident incident, List<String> toAddress, List<String> ccAddress) {

        // TODO 実装

    }

    @Override
    public List<MailSendHistory> mailHistory(Long id) {

        // TODO 実装

        return null;
    }

    @Override
    public List<Incident> history(Long id) {

        // TODO 実装

        return null;
    }
}
