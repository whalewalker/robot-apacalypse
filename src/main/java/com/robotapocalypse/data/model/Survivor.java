package com.robotapocalypse.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class Survivor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String gender;
    private double latitude;
    private double longitude;
    private boolean infected;
    @ElementCollection
    private Map<ResourceType, Integer> inventory;

    public void flagAsInfected() {
        this.infected = true;
    }


    @OneToMany(mappedBy = "reportedSurvivor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContaminationReport> contaminationReports = new HashSet<>();

    public void addContaminationReport(Survivor reportingSurvivor) {
        ContaminationReport report = new ContaminationReport(reportingSurvivor, this);
        contaminationReports.add(report);
        reportingSurvivor.getContaminationReports().add(report);
    }

    public Set<ContaminationReport> getContaminationReports() {
        return contaminationReports;
    }

    public enum ResourceType {
        WATER, FOOD, MEDICATION, AMMUNITION
    }
}
