package com.githealbackend.githealbackend.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "bug_reports")
public class BugReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String repositoryName;
    private Integer issueNumber;
    private String title;
    @Column(columnDefinition = "TEXT") private String description;
    private String status;
    @Column(columnDefinition = "TEXT") private String patchCode;
    private String pullRequestUrl;

    public BugReport() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRepositoryName() { return repositoryName; }
    public void setRepositoryName(String repositoryName) { this.repositoryName = repositoryName; }
    public Integer getIssueNumber() { return issueNumber; }
    public void setIssueNumber(Integer issueNumber) { this.issueNumber = issueNumber; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPatchCode() { return patchCode; }
    public void setPatchCode(String patchCode) { this.patchCode = patchCode; }
    public String getPullRequestUrl() { return pullRequestUrl; }
    public void setPullRequestUrl(String pullRequestUrl) { this.pullRequestUrl = pullRequestUrl; }
}
