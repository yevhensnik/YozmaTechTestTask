package com.yozmatech.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yozmatech.enums.TreatmentAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentPlanDto {

    private TreatmentAction treatmentAction;
    private String patient;

//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime startTime;
//    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime endTime;
    private String recurrencePattern;
}
