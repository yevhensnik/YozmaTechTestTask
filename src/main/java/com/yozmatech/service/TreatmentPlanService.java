package com.yozmatech.service;

import com.yozmatech.dto.TreatmentPlanDto;
import com.yozmatech.entity.TreatmentPlan;
import com.yozmatech.repository.TreatmentPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TreatmentPlanService {

    @Autowired
    private TreatmentPlanRepository treatmentPlanRepository;

    @Autowired
    private TreatmentSchedulerService treatmentSchedulerService;

    public TreatmentPlan createTreatmentPlan(@RequestBody TreatmentPlanDto planDto) {

        TreatmentPlan plan = new TreatmentPlan();
        plan.setTreatmentAction(planDto.getTreatmentAction());
        plan.setPatient(planDto.getPatient());
        plan.setStartTime(planDto.getStartTime());
        plan.setEndTime(planDto.getEndTime());
        plan.setRecurrencePattern(planDto.getRecurrencePattern());

        TreatmentPlan savedTreatmentPlan = treatmentPlanRepository.save(plan);
        treatmentSchedulerService.generateTreatmentTask(savedTreatmentPlan);

        return savedTreatmentPlan;
    }

    public List<TreatmentPlan> getTreatmentPlans() {
        return treatmentPlanRepository.findAll();
    }
}
