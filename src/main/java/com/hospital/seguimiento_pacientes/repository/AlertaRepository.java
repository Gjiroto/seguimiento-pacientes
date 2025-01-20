package com.hospital.seguimiento_pacientes.repository;

import com.hospital.seguimiento_pacientes.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}