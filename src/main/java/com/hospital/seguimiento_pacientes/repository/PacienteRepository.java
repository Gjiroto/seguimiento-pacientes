package com.hospital.seguimiento_pacientes.repository;


import com.hospital.seguimiento_pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}