package com.hospital.seguimiento_pacientes.service;


import com.hospital.seguimiento_pacientes.model.Paciente;
import com.hospital.seguimiento_pacientes.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    // Guardar un paciente
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // Obtener un paciente por ID
    public Paciente obtenerPaciente(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }
    // Obtener todos los pacientes
    public List<Paciente> obtenerTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    // Nuevo método: Eliminar paciente por ID
    public void eliminarPacientePorId(Long pacienteId) {
        if (!pacienteRepository.existsById(pacienteId)) {
            throw new RuntimeException("No se puede eliminar. Paciente con ID " + pacienteId + " no encontrado");
        }
        pacienteRepository.deleteById(pacienteId);
    }
    
    // Eliminar paciente por nombre
    public void eliminarPacientePorNombre(String nombre) {
        Paciente paciente = pacienteRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar. Paciente con nombre " + nombre + " no encontrado"));
        pacienteRepository.delete(paciente);
    }

        // Actualizar parcialmente un paciente
        public Paciente actualizarPacienteParcial(Long pacienteId, Paciente datosActualizados) {
            Paciente pacienteExistente = pacienteRepository.findById(pacienteId)
                    .orElseThrow(() -> new RuntimeException("Paciente con ID " + pacienteId + " no encontrado"));
            
            // Actualizar solo los campos que no son nulos
            if (datosActualizados.getNombre() != null) {
                pacienteExistente.setNombre(datosActualizados.getNombre());
            }
            
            if (datosActualizados.getDiagnostico() != null) {
                pacienteExistente.setDiagnostico(datosActualizados.getDiagnostico());
            }
            
            if (datosActualizados.getFechaNacimiento() != null) {
                pacienteExistente.setFechaNacimiento(datosActualizados.getFechaNacimiento());
            }
            
            if (datosActualizados.getTelefono() != null) {
                pacienteExistente.setTelefono(datosActualizados.getTelefono());
            }
            
            // Guardar y devolver el paciente actualizado
            return pacienteRepository.save(pacienteExistente);
        }
}