package com.robotapocalypse.data.repository;

import com.robotapocalypse.data.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurvivorRepository extends JpaRepository<Survivor, Long> {
    Long countByInfectedTrue();

    Long countByInfectedFalse();

    List<Survivor> findByInfectedTrue();

    List<Survivor> findByInfectedFalse();

    @Query("SELECT DISTINCT s FROM Survivor s " +
            "JOIN s.contaminationReports r " +
            "WHERE r.reportedSurvivor = :survivor")
    List<Survivor> findReportedContaminationBySurvivor(@Param("survivor") Survivor survivor);
}
