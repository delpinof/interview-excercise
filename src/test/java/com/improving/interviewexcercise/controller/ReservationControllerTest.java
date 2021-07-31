package com.improving.interviewexcercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.improving.interviewexcercise.api.Reservation;
import com.improving.interviewexcercise.dao.ReservationEntity;
import com.improving.interviewexcercise.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_BASE_PATH;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_GET_BY_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ActiveProfiles("dev")
public class ReservationControllerTest {

    @MockBean
    private ReservationService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGet() throws Exception {

        final int ID = 1;
        final String RESERVATION = "hotel reservation";
        final LocalDate TIME = LocalDate.now();

        Reservation reservationExpected = Reservation.builder()
                .id(ID)
                .name(RESERVATION)
                .time(TIME)
                .build();

        Mockito.when(service.getReservation(ID))
                .thenReturn(Optional.of(ReservationEntity.builder()
                        .id(ID)
                        .name(RESERVATION)
                        .time(TIME)
                        .build()));

        String responseBodyAsString = mockMvc.perform(get(RESERVATION_BASE_PATH.concat(RESERVATION_GET_BY_ID), 1))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Reservation actualReservation = objectMapper.readValue(responseBodyAsString, Reservation.class);

        assertThat(actualReservation).isEqualTo(reservationExpected);

    }
}
