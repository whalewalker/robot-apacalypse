package com.robotapocalypse.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurvivorDTO {

    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotNull(message = "age cannot be null")
    private int age;
    @NotBlank(message = "gender cannot be blank")
    private String gender;
    @NotNull(message = "latitude cannot be null")
    private double latitude;
    @NotNull(message = "longitude cannot be null")
    private double longitude;
}
