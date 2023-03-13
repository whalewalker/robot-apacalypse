package com.robotapocalypse.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContaminationReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Survivor reportingSurvivor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Survivor reportedSurvivor;


    public ContaminationReport(Survivor reportingSurvivor, Survivor reportedSurvivor) {
        this.reportingSurvivor = reportingSurvivor;
        this.reportedSurvivor = reportedSurvivor;
    }
}
