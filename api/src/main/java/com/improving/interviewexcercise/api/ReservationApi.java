package com.improving.interviewexcercise.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationApi {

    ResponseEntity<Reservation> get(int id);

    List<Reservation> getAll();

    Reservation create(Reservation reservation);

    Reservation update(int id, Reservation reservation);

    ResponseEntity<String> delete(int id);
}
