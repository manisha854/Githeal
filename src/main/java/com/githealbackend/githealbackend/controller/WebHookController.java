package com.githealbackend.githealbackend.controller;

import com.githealbackend.githealbackend.entity.BugReport;
import com.githealbackend.githealbackend.repository.BugReportRepository;
import com.githealbackend.githealbackend.service.AgentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "*")
public class WebHookController {
    private final AgentService service;
    private final BugReportRepository repo;

    public WebHookController(AgentService service, BugReportRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping("/webhook")
    public BugReport receiveWebhook(@RequestBody BugReport payload) {
        return service.processWorkflow(payload);
    }

    @GetMapping("/bugs")
    public List<BugReport> fetchLogs() { return repo.findAll(); }
}
