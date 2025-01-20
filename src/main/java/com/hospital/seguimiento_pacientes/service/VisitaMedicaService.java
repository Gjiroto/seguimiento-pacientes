package com.hospital.seguimiento_pacientes.service;

import com.hospital.seguimiento_pacientes.model.VisitaMedica;
import com.hospital.seguimiento_pacientes.repository.VisitaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitaMedicaService {

    @Autowired
    private VisitaMedicaRepository visitaMedicaRepository;

    public VisitaMedica guardarVisita(VisitaMedica visitaMedica) {
        return visitaMedicaRepository.save(visitaMedica);
    }

    public List<VisitaMedica> obtenerVisitasPorPaciente(Long pacienteId) {
        return visitaMedicaRepository.findByPacienteId(pacienteId);
    }
}