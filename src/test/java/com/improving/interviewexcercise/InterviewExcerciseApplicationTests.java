package com.improving.interviewexcercise;

import com.improving.interviewexcercise.controller.ReservationController;
import com.improving.interviewexcercise.dao.ReservationRepository;
import com.improving.interviewexcercise.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InterviewExcerciseApplicationTests {

    @Autowired
    private ReservationController reservationController;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void contextLoads() {
        assertThat(reservationController).isNotNull();
        assertThat(reservationService).isNotNull();
        assertThat(reservationRepository).isNotNull();
    }

}
