package com.improving.interviewexcercise.service;

import com.improving.interviewexcercise.common.ReservationUtil;
import com.improving.interviewexcercise.dao.ReservationEntity;
import com.improving.interviewexcercise.dao.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService service;

    @MockBean
    private ReservationRepository repository;

    @Test
    public void testGetAll() {
        List<ReservationEntity> expectedReservations = ReservationUtil.generateReservationEntityList();
        when(repository.findAll()).thenReturn(expectedReservations);
        List<ReservationEntity> actualReservations = service.getAllReservations();
        assertThat(actualReservations).containsExactlyInAnyOrderElementsOf(expectedReservations);
    }

    @Test
    public void testDeleteNotFound() {
        when(repository.existsById(any(Integer.class))).thenReturn(false);
        assertThatThrownBy(() -> service.deleteReservation(1)).isInstanceOf(ReservationNotFoundException.class);
    }
}
