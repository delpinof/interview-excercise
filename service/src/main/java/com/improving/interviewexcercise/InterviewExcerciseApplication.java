package com.improving.interviewexcercise;

import com.improving.interviewexcercise.common.ReservationUtil;
import com.improving.interviewexcercise.dao.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
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

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.improving.interviewexcercise.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Reservation Service API",
                "Reservation Service API Description",
                "1.0",
                "http://improving.com/terms",
                new Contact("improving", "https://improving.com", "apis@improving.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }

}
