package com.robotapocalypse.web.controller;

import com.robotapocalypse.data.dto.ApiResponseDTO;
import com.robotapocalypse.service.RobotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/robots")
@RequiredArgsConstructor
public class RobotController {
    private final RobotService robotService;
    @GetMapping
    public ResponseEntity<ApiResponseDTO> getRobots() {
       return ResponseEntity.ok().body(robotService.getRobots());
    }
}
