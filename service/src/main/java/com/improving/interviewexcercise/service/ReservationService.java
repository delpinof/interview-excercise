package com.improving.interviewexcercise.service;

import com.improving.interviewexcercise.dao.ReservationEntity;
import com.improving.interviewexcercise.dao.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_NOT_FOUND;

@Slf4j
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

    public ReservationEntity createReservation(ReservationEntity entity) {
        return repository.save(entity);
    }

    public ReservationEntity updateReservation(ReservationEntity entity) {
        if (!repository.existsById(entity.getId())) {
            log.warn("reservationEvent=updateReservation, reservation don't exist but created anyway");
        }
        return repository.save(entity);
    }

    public void deleteReservation(int reservationId) throws ReservationNotFoundException {
        if (!repository.existsById(reservationId)) {
            log.warn("reservationEvent=deleteReservation, reservation don't exist");
            throw new ReservationNotFoundException(RESERVATION_NOT_FOUND);
        }
        repository.deleteById(reservationId);
    }
}
