package com.improving.interviewexcercise.controller;

import com.improving.interviewexcercise.api.Reservation;
import com.improving.interviewexcercise.dao.ReservationEntity;
import com.improving.interviewexcercise.service.ReservationNotFoundException;
import com.improving.interviewexcercise.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_BASE_PATH;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_BY_ID;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_DELETED;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_GET_ALL;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_NOT_FOUND;

@Slf4j
@Controller
@RequestMapping(RESERVATION_BASE_PATH)
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(RESERVATION_BY_ID)
    public ResponseEntity<Reservation> get(@PathVariable int id) {
        log.info("reservationEvent=getReservation, id={}", id);
        Optional<ReservationEntity> optionalReservationEntity = service.getReservation(id);
        Optional<Reservation> optionalReservation = Optional.empty();
        if (optionalReservationEntity.isPresent())
            optionalReservation = Optional.of(modelMapper.map(optionalReservationEntity.get(), Reservation.class));
        log.debug("reservationEvent=getReservation, reservation={}", optionalReservation);
        return ResponseEntity.of(optionalReservation);
    }

    @GetMapping(RESERVATION_GET_ALL)
    public ResponseEntity<List<Reservation>> getAll() {
        log.info("reservationEvent=getAllReservations");
        List<Reservation> reservationList = service.getAllReservations().stream()
                .map(element -> modelMapper.map(element, Reservation.class))
                .collect(Collectors.toList());
        log.debug("reservationEvent=getAllReservations, reservations={}", reservationList);
        return ResponseEntity.ok(reservationList);
    }

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) {
        log.info("reservationEvent=createReservation");
        ReservationEntity reservationSaved = service.createReservation(modelMapper.map(reservation, ReservationEntity.class));
        log.debug("reservationEvent=reservationSaved, reservation={}", reservationSaved);
        return ResponseEntity.ok(modelMapper.map(reservationSaved, Reservation.class));
    }

    @PutMapping(RESERVATION_BY_ID)
    public ResponseEntity<Reservation> update(@PathVariable int id, @RequestBody Reservation reservation) {
        log.info("reservationEvent=updateReservation");
        ReservationEntity reservationEntity = modelMapper.map(reservation, ReservationEntity.class);
        reservationEntity.setId(id);
        reservationEntity = service.updateReservation(reservationEntity);
        log.debug("reservationEvent=reservationSaved, reservation={}", reservationEntity);
        return ResponseEntity.ok(modelMapper.map(reservationEntity, Reservation.class));
    }

    @DeleteMapping(RESERVATION_BY_ID)
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            service.deleteReservation(id);
        } catch (ReservationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RESERVATION_NOT_FOUND);
        }
        return ResponseEntity.ok(RESERVATION_DELETED);
    }
}
