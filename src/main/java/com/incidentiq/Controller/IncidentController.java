package com.incidentiq.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incidentiq.Model.Incident;
import com.incidentiq.service.IncidentService;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @PostMapping
    public Incident createIncident(@RequestBody Incident incident) {
        return service.save(incident);
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Incident getIncident(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<Incident> searchIncidents(@RequestParam String title) {
        return service.search(title);
    }

    @GetMapping("/status/{status}")
    public List<Incident> getByStatus(@PathVariable String status) {
        return service.getByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<Incident> getByPriority(@PathVariable String priority) {
        return service.getByPriority(priority);
    }

    @GetMapping("/severity/{severity}")
    public List<Incident> getBySeverity(@PathVariable String severity) {
        return service.getBySeverity(severity);
    }

    @PutMapping("/{id}")
    public Incident updateIncident(
            @PathVariable Long id,
            @RequestBody Incident incident) {

        return service.update(id, incident);
    }

    @PutMapping("/{id}/assign")
    public Incident assignIncident(
            @PathVariable Long id,
            @RequestParam String assignee) {

        Incident incident = service.getById(id);

        if (incident == null) {
            return null;
        }

        incident.setAssignee(assignee);

        return service.save(incident);
    }

    @DeleteMapping("/{id}")
    public String deleteIncident(@PathVariable Long id) {
        service.delete(id);
        return "Incident deleted successfully";
    }
}