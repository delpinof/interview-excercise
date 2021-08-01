package com.improving.interviewexcercise;

import com.improving.interviewexcercise.common.ReservationUtil;
import com.improving.interviewexcercise.dao.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InterviewExcerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewExcerciseApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner loadDB(ReservationRepository repository) {
        return (args) -> {
            repository.saveAll(ReservationUtil.generateReservationEntityList());
        };

    }

}
