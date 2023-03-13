package com.robotapocalypse.service;

import com.robotapocalypse.data.dto.SurvivorDTO;
import com.robotapocalypse.data.model.ContaminationReport;
import com.robotapocalypse.data.model.Survivor;
import com.robotapocalypse.data.repository.ContaminationReportRepository;
import com.robotapocalypse.data.repository.SurvivorRepository;
import com.robotapocalypse.web.exception.RobotApocalypseException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SurvivorServiceImpl implements SurvivorService {
    private final SurvivorRepository survivorRepository;
    private final ContaminationReportRepository contaminationReportRepository;
    private final ModelMapper mapper;

    public Survivor addSurvivor(SurvivorDTO survivorDTO) {
        Survivor survivor = mapper.map(survivorDTO, Survivor.class);
        // Set the default inventory for the new survivor
        Map<Survivor.ResourceType, Integer> inventory = new HashMap<>();
        inventory.put(Survivor.ResourceType.WATER, 0);
        inventory.put(Survivor.ResourceType.FOOD, 0);
        inventory.put(Survivor.ResourceType.MEDICATION, 0);
        inventory.put(Survivor.ResourceType.AMMUNITION, 0);

        survivor.setInventory(inventory);
        return survivorRepository.save(survivor);
    }

    public Survivor updateSurvivorLocation(Long id) throws RobotApocalypseException {
        Survivor survivor = survivorRepository.findById(id)
                .orElseThrow(() -> new RobotApocalypseException("Survivor not found with id: " + id));
        return survivorRepository.save(survivor);
    }

    @Override
    public Set<ContaminationReport> getContaminationReportsForSurvivor(Long id) throws RobotApocalypseException {
        Survivor survivor = survivorRepository.findById(id)
                .orElseThrow(() -> new RobotApocalypseException("Survivor not found with id: " + id));
        return survivor.getContaminationReports();
    }

    @Override
    public void reportSurvivorAsInfected(Long reportingSurvivorId, Long reportedSurvivorId) throws RobotApocalypseException {
        Survivor reportingSurvivor = survivorRepository.findById(reportingSurvivorId)
                .orElseThrow(() -> new RobotApocalypseException("Survivor not found with id: " + reportingSurvivorId));
        Survivor reportedSurvivor = survivorRepository.findById(reportedSurvivorId)
                .orElseThrow(() -> new RobotApocalypseException("Survivor not found with id: " + reportedSurvivorId));
        addContaminationReport(reportingSurvivor, reportedSurvivor);
        survivorRepository.save(reportedSurvivor);
        contaminationReportRepository.save(new ContaminationReport(reportingSurvivor, reportedSurvivor));
    }

    public void addContaminationReport(Survivor reportingSurvivor, Survivor reportedSurvivor) {
        ContaminationReport report = new ContaminationReport(reportingSurvivor, reportedSurvivor);
        reportedSurvivor.getContaminationReports().add(report);
        reportingSurvivor.getContaminationReports().add(report);
    }

    public void flagSurvivorAsInfected(Long id) throws RobotApocalypseException {
        Survivor survivor = survivorRepository.findById(id)
                .orElseThrow(() -> new RobotApocalypseException("Survivor not found with id: " + id));

        List<Survivor> reportedContamination = survivorRepository.findReportedContaminationBySurvivor(survivor);
        if (reportedContamination.size() >= 3) {
            survivor.flagAsInfected();
            survivorRepository.save(survivor);
        } else {
            throw new RobotApocalypseException("Insufficient contamination reports for survivor with id: " + id);
        }
    }


    public Double getInfectedPercentage() {
        long totalSurvivors = survivorRepository.count();
        Long infectedSurvivors = survivorRepository.countByInfectedTrue();
        return ((double) infectedSurvivors / totalSurvivors) * 100;
    }

    public Double getNonInfectedPercentage() {
        long totalSurvivors = survivorRepository.count();
        Long nonInfectedSurvivors = survivorRepository.countByInfectedFalse();
        return ((double) nonInfectedSurvivors / totalSurvivors) * 100;
    }

    @Override
    public List<Survivor> getInfectedSurvivors() {
        return survivorRepository.findByInfectedTrue();
    }

    @Override
    public List<Survivor> getNonInfectedSurvivors() {
        return survivorRepository.findByInfectedFalse();
    }

}
