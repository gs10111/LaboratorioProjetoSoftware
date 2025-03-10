package com.sistema_matricula.sismatricula;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SismatriculaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SismatriculaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        MainMock.main(args);
    }
}
