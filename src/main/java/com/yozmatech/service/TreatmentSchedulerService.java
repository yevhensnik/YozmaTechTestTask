package com.yozmatech.service;

import com.yozmatech.entity.TaskStatus;
import com.yozmatech.entity.TreatmentPlan;
import com.yozmatech.entity.TreatmentTask;
import com.yozmatech.repository.TreatmentPlanRepository;
import com.yozmatech.repository.TreatmentTaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.yozmatech.util.RecurrencePatternParser.generateList;

@Service
public class TreatmentSchedulerService {

    @Autowired
    private TreatmentPlanRepository planRepository;

    @Autowired
    private TreatmentTaskRepository taskRepository;

    @PostConstruct
    public void generateTreatmentTasks() {
        System.out.println("Started generateTreatmentTasks proccess!!!");
        Iterable<TreatmentPlan> plans = planRepository.findAll();

        for (TreatmentPlan plan : plans) {
            List<LocalDateTime> schedule = generateList(plan.getRecurrencePattern(), plan.getStartTime().toLocalDate(), plan.getEndTime().toLocalDate());

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            for (int i = 0; i < schedule.size(); i++) {
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime dateTime = schedule.get(i);
                if(currentTime.isAfter(dateTime)) {
                    continue;
                }

                TaskStatus status = (i == schedule.size() - 1) ? TaskStatus.COMPLETED : TaskStatus.ACTIVE;

                if (plan.getStartTime().isBefore(dateTime) &&
                        (plan.getEndTime() == null || plan.getEndTime().isAfter(dateTime))) {
                    scheduler.schedule(() -> runJob(plan, dateTime, status), getTimeDifferenceMillis(LocalDateTime.now(), dateTime), TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    public void generateTreatmentTask(TreatmentPlan plan) {
        System.out.println("Started generateTreatmentTask for new PLAN!!!");

        List<LocalDateTime> schedule = generateList(plan.getRecurrencePattern(), plan.getStartTime().toLocalDate(), plan.getEndTime().toLocalDate());

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        for (int i = 0; i < schedule.size(); i++) {
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime dateTime = schedule.get(i);
            if(currentTime.isAfter(dateTime)) {
                continue;
            }

            TaskStatus status = (i == schedule.size() - 1) ? TaskStatus.COMPLETED : TaskStatus.ACTIVE;

            if (plan.getStartTime().isBefore(dateTime) &&
                    (plan.getEndTime() == null || plan.getEndTime().isAfter(dateTime))) {
                scheduler.schedule(() -> runJob(plan, dateTime, status), getTimeDifferenceMillis(LocalDateTime.now(), dateTime), TimeUnit.MILLISECONDS);
            }
        }
    }

    public void runJob(TreatmentPlan plan, LocalDateTime currentTime, TaskStatus taskStatus) {
        TreatmentTask task = new TreatmentTask();
        task.setTreatmentAction(plan.getTreatmentAction());
        task.setPatient(plan.getPatient());
        task.setStartTime(currentTime);
        task.setStatus(taskStatus);
        task.setTreatmentPlanId(plan.getId());
        taskRepository.save(task);
    }

    public long getTimeDifferenceMillis(LocalDateTime now, LocalDateTime futureDateTime) {
        return java.time.Duration.between(now, futureDateTime).toMillis();
    }
}
