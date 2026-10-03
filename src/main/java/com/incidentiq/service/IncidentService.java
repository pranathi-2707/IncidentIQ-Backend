package com.incidentiq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.incidentiq.Model.Incident;
import com.incidentiq.repository.IncidentRepository;

@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public Incident save(Incident incident) {
        return repository.save(incident);
    }

    public List<Incident> getAll() {
        return repository.findAll();
    }

    public Incident getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Incident update(Long id, Incident incident) {

        Incident existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setTitle(incident.getTitle());
        existing.setDescription(incident.getDescription());
        existing.setCategory(incident.getCategory());
        existing.setPriority(incident.getPriority());
        existing.setSeverity(incident.getSeverity());
        existing.setStatus(incident.getStatus());
        existing.setReporter(incident.getReporter());
        existing.setAssignee(incident.getAssignee());

        return repository.save(existing);
    }

    public List<Incident> search(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public List<Incident> getByStatus(String status) {
        return repository.findByStatusIgnoreCase(status);
    }

    public List<Incident> getByPriority(String priority) {
        return repository.findByPriorityIgnoreCase(priority);
    }

    public List<Incident> getBySeverity(String severity) {
        return repository.findBySeverityIgnoreCase(severity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}