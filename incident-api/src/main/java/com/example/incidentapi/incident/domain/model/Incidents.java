package com.example.incidentapi.incident.domain.model;

import com.example.incidentapi.incident.presentation.dto.IncidentResource;
import com.github.dozermapper.core.Mapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Incidents {

    private final List<Incident> list;

    private final Mapper beanMapper;

    public Page<IncidentResource> getResources() {
        return new PageImpl<>(
                list.stream()
                        .map(from -> beanMapper.map(from, IncidentResource.class))
                        .collect(Collectors.toList())
        );
    }
}
