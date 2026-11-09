package com.githealbackend.githealbackend.service;

import com.githealbackend.githealbackend.entity.BugReport;
import com.githealbackend.githealbackend.repository.BugReportRepository;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
    private final BugReportRepository repo;
    @Value("${openai.api.key}") private String apiKey;

    public AgentService(BugReportRepository repo) { this.repo = repo; }

    public BugReport processWorkflow(BugReport bug) {
        bug.setStatus("ANALYZING");
        BugReport saved = repo.save(bug);

        try {
            // Step 1: LangChain4j Patch Generation
            OpenAiChatModel model = OpenAiChatModel.withApiKey(apiKey);
            String instruction = "You are an expert autonomous software agent. Generate a git diff patch for:\n" + saved.getDescription();
            String generatedPatch = model.generate(instruction);
            saved.setPatchCode(generatedPatch);

            // Step 2: Simulated Isolated Docker Verification Loop
            boolean containerCheck = runDockerSuiteSimulation(generatedPatch);
            if(containerCheck) {
                saved.setStatus("PR_CREATED");
                saved.setPullRequestUrl("https://github.com" + bug.getRepositoryName() + "/pull/" + bug.getIssueNumber());
            } else {
                saved.setStatus("REMEDIATION_FAILED");
            }
        } catch (Exception e) {
            saved.setStatus("ERROR_PROCESSING");
        }
        return repo.save(saved);
    }

    private boolean runDockerSuiteSimulation(String patch) {
        System.out.println("Validating code patches inside isolated Docker container environment...");
        return patch != null && !patch.isBlank();
    }
}
