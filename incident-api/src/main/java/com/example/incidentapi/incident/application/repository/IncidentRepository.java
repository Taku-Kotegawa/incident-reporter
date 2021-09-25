package com.example.incidentapi.incident.application.repository;

import com.example.incidentapi.incident.domain.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
