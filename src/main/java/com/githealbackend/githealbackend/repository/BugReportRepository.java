package com.githealbackend.githealbackend.repository;

import com.githealbackend.githealbackend.entity.BugReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugReportRepository extends JpaRepository<BugReport, Long> {}
