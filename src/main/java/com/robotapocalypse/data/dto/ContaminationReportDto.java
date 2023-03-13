package com.robotapocalypse.data.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ContaminationReportDto {
    @NotNull(message = "reportedSurvivorId cannot be null")
    private Long reportedSurvivorId;

    @NotNull(message = "reportingSurvivorId cannot be null")
    private Long reportingSurvivorId;
}
