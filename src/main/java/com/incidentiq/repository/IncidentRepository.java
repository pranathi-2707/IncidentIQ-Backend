package com.incidentiq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incidentiq.Model.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    List<Incident> findByTitleContainingIgnoreCase(String title);

    List<Incident> findByStatusIgnoreCase(String status);

    List<Incident> findByPriorityIgnoreCase(String priority);

    List<Incident> findBySeverityIgnoreCase(String severity);
}