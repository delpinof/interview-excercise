package com.improving.interviewexcercise.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.improving.interviewexcercise.api.Reservation;
import com.improving.interviewexcercise.common.ReservationUtil;
import com.improving.interviewexcercise.dao.ReservationEntity;
import com.improving.interviewexcercise.service.ReservationNotFoundException;
import com.improving.interviewexcercise.service.ReservationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_BASE_PATH;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_BY_ID;
import static com.improving.interviewexcercise.api.ReservationApiConstants.RESERVATION_GET_ALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = {ReservationController.class, ModelMapper.class})
@ActiveProfiles("dev")
public class ReservationControllerTest {

    @MockBean
    private ReservationService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static List<Reservation> reservationList;

    private static List<ReservationEntity> reservationEntityList;

    @BeforeAll
    public static void setUp() {
        reservationList = ReservationUtil.generateReservationList();
        reservationEntityList = ReservationUtil.generateReservationEntityList();
    }

    @Test
    public void testGetSuccess() throws Exception {

        Reservation reservationExpected = reservationList.get(0);

        Mockito.when(service.getReservation(reservationExpected.getId()))
                .thenReturn(Optional.of(reservationEntityList.get(0)));

        String responseBodyAsString = mockMvc.perform(
                get(RESERVATION_BASE_PATH.concat(RESERVATION_BY_ID), reservationExpected.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Reservation actualReservation = objectMapper.readValue(responseBodyAsString, Reservation.class);

        assertThat(actualReservation).isEqualTo(reservationExpected);

    }

    @Test
    public void testGetNotFound() throws Exception {
        int id = -1;
        Mockito.when(service.getReservation(id)).thenReturn(Optional.empty());
        mockMvc.perform(get(RESERVATION_BASE_PATH.concat(RESERVATION_BY_ID), id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAll() throws Exception {
        Mockito.when(service.getAllReservations()).thenReturn(ReservationUtil.generateReservationEntityList());
        String bodyResponseAsString = mockMvc.perform(get(RESERVATION_BASE_PATH.concat(RESERVATION_GET_ALL)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Reservation> reservationList = objectMapper.readValue(bodyResponseAsString, new TypeReference<List<Reservation>>() {
        });
        assertThat(reservationList).containsExactlyInAnyOrderElementsOf(ReservationUtil.generateReservationList());
    }

    @Test
    public void testCreate() throws Exception {
        Reservation reservationExpected = reservationList.get(0);
        Mockito.when(service.createReservation(any(ReservationEntity.class))).thenReturn(reservationEntityList.get(0));
        String bodyResponseAsString = mockMvc.perform(
                post(RESERVATION_BASE_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationExpected)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Reservation actualReservation = objectMapper.readValue(bodyResponseAsString, Reservation.class);

        assertThat(actualReservation).isEqualTo(reservationExpected);
    }

    @Test
    public void testUpdate() throws Exception {
        Reservation reservationExpected = reservationList.get(0);
        Mockito.when(service.updateReservation(any(ReservationEntity.class))).thenReturn(reservationEntityList.get(0));
        String bodyResponseAsString = mockMvc.perform(
                put(RESERVATION_BASE_PATH.concat(RESERVATION_BY_ID), reservationExpected.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationExpected)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Reservation actualReservation = objectMapper.readValue(bodyResponseAsString, Reservation.class);

        assertThat(actualReservation).isEqualTo(reservationExpected);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        int id = 1;
        Mockito.doThrow(new ReservationNotFoundException("Reservation Not Found")).when(service).deleteReservation(id);
        mockMvc.perform(delete(RESERVATION_BASE_PATH.concat(RESERVATION_BY_ID), id))
                .andExpect(status().isNotFound());
    }
}
