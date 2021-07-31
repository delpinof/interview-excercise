package com.improving.interviewexcercise.controller;

import com.improving.interviewexcercise.api.Reservation;
import com.improving.interviewexcercise.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_BASE_PATH;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_GET_BY_ID;

@Slf4j
@Controller
@RequestMapping(RESERVATION_BASE_PATH)
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping(RESERVATION_GET_BY_ID)
    public @ResponseBody Reservation get(@PathVariable int id) {
        Reservation reservation = service.getReservation(id);
        log.debug("reservationEvent=getReservation, id={}", id);
        return reservation;
    }
}
