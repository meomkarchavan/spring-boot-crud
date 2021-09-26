package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {

        return args -> {
            Student omkar = new Student(1L,
                    "Omkar",
                    "omkarc@mkcl.org",
                    LocalDate.of(1999, Month.SEPTEMBER, 28)

            );
            Student ankit = new Student(2L,
                    "Ankit",
                    "Ankit@mkcl.org",
                    LocalDate.of(1994, Month.OCTOBER, 27)

            );
            repository.saveAll(Arrays.asList(omkar, ankit));
        };
    }
}
