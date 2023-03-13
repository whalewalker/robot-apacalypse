package com.robotapocalypse.web.controller;

import com.robotapocalypse.data.dto.ApiResponseDTO;
import com.robotapocalypse.data.dto.ContaminationReportDto;
import com.robotapocalypse.data.dto.SurvivorDTO;
import com.robotapocalypse.data.model.ContaminationReport;
import com.robotapocalypse.data.model.Survivor;
import com.robotapocalypse.service.SurvivorService;
import com.robotapocalypse.web.exception.RobotApocalypseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/survivors")
@RequiredArgsConstructor
public class SurvivorController {
    private final SurvivorService survivorService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<Survivor>> addSurvivor(@RequestBody @Valid SurvivorDTO survivor) {
        return ResponseEntity.ok(new ApiResponseDTO<>(true, "Survivor added successfully", survivorService.addSurvivor(survivor)));
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<ApiResponseDTO<Survivor>> updateSurvivorLocation(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new ApiResponseDTO<>(true, "Survivor updated successfully", survivorService.updateSurvivorLocation(id)));
        } catch (RobotApocalypseException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}/infected")
    public ResponseEntity<ApiResponseDTO<Void>> flagSurvivorAsInfected(@PathVariable Long id) {
        try {
            survivorService.flagSurvivorAsInfected(id);
            return ResponseEntity.ok().body(new ApiResponseDTO<>(true, "Survivor successfully flagged as infected"));
        } catch (RobotApocalypseException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, e.getMessage()));
        }
    }

    @GetMapping("/infectedPercentage")
    public ResponseEntity<ApiResponseDTO<Double>> getInfectedPercentage() {
        return ResponseEntity.ok().body(new ApiResponseDTO<>(true, "Successfully fetch infected survivor percentage", survivorService.getInfectedPercentage()));
    }

    @GetMapping("/nonInfectedPercentage")
    public ResponseEntity<ApiResponseDTO<Double>> getNonInfectedPercentage() {
        return ResponseEntity.ok().body(new ApiResponseDTO<>(true, "Successfully fetch non-infected survivor percentage", survivorService.getNonInfectedPercentage()));
    }

    @GetMapping("/infectedSurvivors")
    public ResponseEntity<ApiResponseDTO<List<Survivor>>> getInfectedSurvivors() {
        List<Survivor> infectedSurvivors = survivorService.getInfectedSurvivors();
        return ResponseEntity.ok().body(new ApiResponseDTO<>(true, "Successfully fetch infected survivor", infectedSurvivors));
    }

    @GetMapping("/nonInfectedSurvivors")
    public ResponseEntity<ApiResponseDTO<List<Survivor>>> getNonInfectedSurvivors() {
        return ResponseEntity.ok().body(new ApiResponseDTO<>(true, "Successfully fetch non-infected survivor", survivorService.getNonInfectedSurvivors()));
    }

    @GetMapping("/{id}/contaminationReports")
    public ResponseEntity<ApiResponseDTO<Set<ContaminationReport>>> getContaminationReportsForSurvivor(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(new ApiResponseDTO<>(true, "Successfully fetch contamination report", survivorService.getContaminationReportsForSurvivor(id)));
        } catch (RobotApocalypseException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, e.getMessage()));
        }
    }

    @PostMapping("/reportInfected")
    public ResponseEntity<ApiResponseDTO<Void>> reportSurvivorAsInfected(@RequestBody @Valid ContaminationReportDto reportDto) {
        try {
            survivorService.reportSurvivorAsInfected(reportDto.getReportingSurvivorId(), reportDto.getReportedSurvivorId());
            return ResponseEntity.ok(new ApiResponseDTO<>(true, "Successfully report survivor as infected"));
        } catch (RobotApocalypseException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, e.getMessage()));
        }
    }
}
