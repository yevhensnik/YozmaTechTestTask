package com.yozmatech.controller;

import com.yozmatech.dto.TreatmentPlanDto;
import com.yozmatech.entity.TreatmentPlan;
import com.yozmatech.service.TreatmentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatmentplan")
public class TreatmentPlanController {

    @Autowired
    private TreatmentPlanService treatmentPlanService;

    @PostMapping("/create")
    public ResponseEntity<TreatmentPlan> createTreatmentPlan(@RequestBody TreatmentPlanDto planDto) {
        return ResponseEntity.ok(treatmentPlanService.createTreatmentPlan(planDto));
    }


    @GetMapping("/get")
    public ResponseEntity<List<TreatmentPlan>> getTreatmentPlans() {
        return ResponseEntity.ok(treatmentPlanService.getTreatmentPlans());
    }
}
