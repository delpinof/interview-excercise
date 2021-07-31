package com.improving.interviewexcercise.service;

import com.improving.interviewexcercise.api.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationService {

    public Reservation getReservation(int reservationId) {
        return Reservation.builder().build();
    }
}
