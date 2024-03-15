package com.yozmatech.controller;

import com.yozmatech.dto.TreatmentPlanDto;
import com.yozmatech.entity.TreatmentPlan;
import com.yozmatech.entity.TreatmentTask;
import com.yozmatech.repository.TreatmentTaskRepository;
import com.yozmatech.service.TreatmentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatmenttask")
public class TreatmentTaskController {

    @Autowired
    private TreatmentTaskRepository treatmentTaskRepository;

    @GetMapping("/get")
    public ResponseEntity<List<TreatmentTask>> getTreatmentTasks() {
        return ResponseEntity.ok(treatmentTaskRepository.findAll());
    }
}
