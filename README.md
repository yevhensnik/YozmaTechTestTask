### TreatmentSchedulerService.java is the main class with scheduler.

#
### It has two options:
* It starts all treatment plans after Application startUp.
* It start separate sheduller after adding new TratmentPlan.

### I have TreatmentPlanController with two endpoints:

* POST [localhost:8080/treatmentplan/create] create new plan
* GET [localhost:8080/treatmentplan/get] return all plans


##### Body example:
```json
{
  "treatmentAction":"ActionTwo",
  "patient":"snik2",
  "startTime":"2024-03-10T01:00:00",
  "endTime":"2024-03-15T23:59:59",
  "recurrencePattern":"every day at 23:41 and 23:42"
}
```
