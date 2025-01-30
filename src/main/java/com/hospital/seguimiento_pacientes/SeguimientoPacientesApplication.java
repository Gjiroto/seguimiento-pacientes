package com.hospital.seguimiento_pacientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // Habilita las tareas programadas
public class SeguimientoPacientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeguimientoPacientesApplication.class, args);
	}
}