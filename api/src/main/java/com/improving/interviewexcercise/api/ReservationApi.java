package com.improving.interviewexcercise.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationApi {

    ResponseEntity<Reservation> get(int id);

    ResponseEntity<List<Reservation>> getAll();

    ResponseEntity<Reservation> create(Reservation reservation);

    ResponseEntity<Reservation> update(int id, Reservation reservation);

    ResponseEntity<String> delete(int id);
}
