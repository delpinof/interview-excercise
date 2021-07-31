package com.improving.interviewexcercise.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Reservation {
    private int id;
    private String name;
    private LocalDate time;
}
