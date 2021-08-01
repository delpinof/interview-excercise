package com.improving.interviewexcercise.service;

import com.improving.interviewexcercise.dao.ReservationEntity;
import com.improving.interviewexcercise.dao.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Optional<ReservationEntity> getReservation(int reservationId) {
        return repository.findById(reservationId);
    }

    public List<ReservationEntity> getAllReservations() {
        List<ReservationEntity> reservationEntityList = new ArrayList<>();
        repository.findAll().forEach(reservationEntity -> reservationEntityList.add(reservationEntity));
        return reservationEntityList;
    }
}
