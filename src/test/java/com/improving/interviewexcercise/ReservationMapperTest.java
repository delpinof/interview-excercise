package com.improving.interviewexcercise;

import com.improving.interviewexcercise.api.Reservation;
import com.improving.interviewexcercise.dao.ReservationEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class ReservationMapperTest {

    private static ModelMapper modelMapper;

    private static Reservation reservation;

    private static ReservationEntity reservationEntity;

    @BeforeAll
    public static void setUp() {
        modelMapper = new ModelMapper();
        final int ID = 1;
        final String RESERVATION = "hotel reservation";
        final LocalDate TIME = LocalDate.now();
        reservation = Reservation.builder()
                .id(ID)
                .name(RESERVATION)
                .time(TIME)
                .build();
        reservationEntity = ReservationEntity.builder()
                .id(ID)
                .name(RESERVATION)
                .time(TIME)
                .build();
    }

    @Test
    public void testEntityToReservationMapper() {
        Reservation actualReservation = modelMapper.map(reservationEntity, Reservation.class);
        assertThat(actualReservation).isEqualTo(reservation);
    }

    @Test
    public void testReservationToEntityMapper() {
        ReservationEntity actualReservationEntity = modelMapper.map(reservation, ReservationEntity.class);
        assertThat(actualReservationEntity).isEqualTo(reservationEntity);
    }
}
