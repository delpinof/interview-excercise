package com.improving.interviewexcercise.common;

import com.improving.interviewexcercise.api.Reservation;
import com.improving.interviewexcercise.dao.ReservationEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationUtil {

    public static List<Reservation> generateReservationList() {
        List<Reservation> reservationList = new ArrayList<>();

        reservationList.add(Reservation.builder()
                .id(1)
                .name("hotel")
                .time(LocalDate.of(2021, 1, 1))
                .build());
        reservationList.add(Reservation.builder()
                .id(2)
                .name("vacation")
                .time(LocalDate.of(2021, 5, 7))
                .build());
        reservationList.add(Reservation.builder()
                .id(3)
                .name("motel")
                .time(LocalDate.of(2021, 2, 28))
                .build());
        reservationList.add(Reservation.builder()
                .id(4)
                .name("external")
                .time(LocalDate.of(2021, 3, 5))
                .build());
        reservationList.add(Reservation.builder()
                .id(5)
                .name("domestic")
                .time(LocalDate.of(2021, 8, 25))
                .build());
        return reservationList;
    }

    public static List<ReservationEntity> generateReservationEntityList() {
        List<ReservationEntity> reservationEntityList = new ArrayList<>();

        reservationEntityList.add(ReservationEntity.builder()
                .id(1)
                .name("hotel")
                .time(LocalDate.of(2021, 1, 1))
                .build());
        reservationEntityList.add(ReservationEntity.builder()
                .id(2)
                .name("vacation")
                .time(LocalDate.of(2021, 5, 7))
                .build());
        reservationEntityList.add(ReservationEntity.builder()
                .id(3)
                .name("motel")
                .time(LocalDate.of(2021, 2, 28))
                .build());
        reservationEntityList.add(ReservationEntity.builder()
                .id(4)
                .name("external")
                .time(LocalDate.of(2021, 3, 5))
                .build());
        reservationEntityList.add(ReservationEntity.builder()
                .id(5)
                .name("domestic")
                .time(LocalDate.of(2021, 8, 25))
                .build());
        return reservationEntityList;
    }
}
