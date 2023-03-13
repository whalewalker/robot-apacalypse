package com.robotapocalypse.service;

import com.robotapocalypse.data.dto.SurvivorDTO;
import com.robotapocalypse.data.model.ContaminationReport;
import com.robotapocalypse.data.model.Survivor;
import com.robotapocalypse.web.exception.RobotApocalypseException;

import java.util.List;
import java.util.Set;

public interface SurvivorService {
    Survivor addSurvivor(SurvivorDTO survivor);

    Survivor updateSurvivorLocation(Long id) throws RobotApocalypseException;

    void flagSurvivorAsInfected(Long id) throws RobotApocalypseException;

    Double getInfectedPercentage();

    Double getNonInfectedPercentage();

    List<Survivor> getInfectedSurvivors();

    List<Survivor> getNonInfectedSurvivors();

    Set<ContaminationReport> getContaminationReportsForSurvivor(Long id) throws RobotApocalypseException;
    void reportSurvivorAsInfected(Long reportingSurvivorId, Long reportedSurvivorId) throws RobotApocalypseException;

}
