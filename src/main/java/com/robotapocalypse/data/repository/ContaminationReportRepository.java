package com.robotapocalypse.data.repository;

import com.robotapocalypse.data.model.ContaminationReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaminationReportRepository extends JpaRepository<ContaminationReport, Long> {
}
