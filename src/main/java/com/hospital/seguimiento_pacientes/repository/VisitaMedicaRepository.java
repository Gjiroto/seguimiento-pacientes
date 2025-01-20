package com.hospital.seguimiento_pacientes.repository;
import com.hospital.seguimiento_pacientes.model.VisitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitaMedicaRepository extends JpaRepository<VisitaMedica, Long> {
    List<VisitaMedica> findByPacienteId(Long pacienteId);
}