package com.improving.interviewexcercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.improving.interviewexcercise.api.Reservation;
import com.improving.interviewexcercise.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_BASE_PATH;
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

        Reservation reservationExpected = Reservation.builder()
                .id(1)
                .name("hotel")
                .time(LocalDate.now())
                .build();

        Mockito.when(service.getReservation(1))
                .thenReturn(reservationExpected);

        String responseBodyAsString = mockMvc.perform(get(RESERVATION_BASE_PATH.concat("/1")))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Reservation actualReservation = objectMapper.readValue(responseBodyAsString, Reservation.class);

        assertThat(actualReservation).isEqualTo(reservationExpected);

    }
}
