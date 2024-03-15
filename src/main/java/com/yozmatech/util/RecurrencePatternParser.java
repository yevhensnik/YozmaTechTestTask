package com.yozmatech.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecurrencePatternParser {

    public static List<LocalDateTime> generateList(String pattern, LocalDate startDate, LocalDate endDate) {
        String[] parts = pattern.split("at");
        String dayPart = parts[0].trim().substring("every".length()).trim();
        String timePart = parts[1].trim();

        List<DayOfWeek> daysOfWeek = parseDaysOfWeek(dayPart);
        List<LocalTime> times = parseTimes(timePart);

        List<LocalDateTime> schedule = generateSchedule(startDate, endDate, daysOfWeek, times);

        for (LocalDateTime dateTime : schedule) {
            System.out.println(dateTime);
        }

        return schedule;
    }

    public static List<LocalDateTime> generateSchedule(LocalDate startDate, LocalDate endDate,
                                                       List<DayOfWeek> daysOfWeek, List<LocalTime> times) {
        List<LocalDateTime> schedule = new ArrayList<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            for (DayOfWeek dayOfWeek : daysOfWeek) {
                if (currentDate.getDayOfWeek() == dayOfWeek) {
                    for (LocalTime time : times) {
                        LocalDateTime dateTime = LocalDateTime.of(currentDate, time);
                        schedule.add(dateTime);
                    }
                }
            }
            currentDate = currentDate.plusDays(1);
        }

        return schedule;
    }

    public static List<DayOfWeek> parseDaysOfWeek(String dayPart) {
        List<DayOfWeek> daysOfWeek = new ArrayList<>();
        if (dayPart.equalsIgnoreCase("day")) {
            daysOfWeek = List.of(DayOfWeek.values());
        } else {
            String[] dayTokens = dayPart.split("and");
            for (String token : dayTokens) {
                DayOfWeek day = DayOfWeek.valueOf(token.trim().toUpperCase());
                daysOfWeek.add(day);
            }
        }
        return daysOfWeek;
    }

    public static List<LocalTime> parseTimes(String timePart) {
        List<LocalTime> times = new ArrayList<>();
        String[] timeTokens = timePart.split("and");
        for (String token : timeTokens) {
            LocalTime time = LocalTime.parse(token.trim(), DateTimeFormatter.ofPattern("HH:mm"));
            times.add(time);
        }
        return times;
    }
}
