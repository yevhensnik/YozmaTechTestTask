package com.yozmatech;

import com.yozmatech.util.RecurrencePatternParser;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class YozmaTechTestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(YozmaTechTestTaskApplication.class, args);
    }

}
