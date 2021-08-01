package com.improving.interviewexcercise.controller;

import com.improving.interviewexcercise.api.Reservation;
import com.improving.interviewexcercise.dao.ReservationEntity;
import com.improving.interviewexcercise.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_BASE_PATH;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_GET_ALL;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_GET_BY_ID;

@Slf4j
@Controller
@RequestMapping(RESERVATION_BASE_PATH)
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(RESERVATION_GET_BY_ID)
    @ResponseBody
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
    @ResponseBody
    public ResponseEntity<List<Reservation>> getAll() {
        log.info("reservationEvent=getAllReservations");
        List<Reservation> reservationList = service.getAllReservations().stream()
                .map(element -> modelMapper.map(element, Reservation.class))
                .collect(Collectors.toList());
        log.debug("reservationEvent=getAllReservations, reservations={}", reservationList);
        return ResponseEntity.ok(reservationList);
    }
}
