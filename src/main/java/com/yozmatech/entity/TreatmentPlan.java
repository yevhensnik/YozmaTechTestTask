package com.yozmatech.entity;

import com.yozmatech.enums.TreatmentAction;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TreatmentPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TreatmentAction treatmentAction;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
    private String patient;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String recurrencePattern;
}
