package com.hospital.seguimiento_pacientes.controller;

import com.hospital.seguimiento_pacientes.model.Paciente;
import com.hospital.seguimiento_pacientes.model.VisitaMedica;
import com.hospital.seguimiento_pacientes.service.PacienteService;
import com.hospital.seguimiento_pacientes.service.VisitaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private VisitaMedicaService visitaMedicaService;

    // Crear paciente
    @PostMapping
    public Paciente crearPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);
    }

    // Obtener todos los pacientes
    @GetMapping
    public List<Paciente> obtenerTodosPacientes() {
        return pacienteService.obtenerTodosLosPacientes();
    }

    // Agregar visita médica a un paciente
    @PostMapping("/{pacienteId}/visitas")
    public VisitaMedica agregarVisita(@PathVariable Long pacienteId, @RequestBody VisitaMedica visitaMedica) {
        Paciente paciente = pacienteService.obtenerPaciente(pacienteId);
        visitaMedica.setNombrePaciente(paciente.getNombre());
        visitaMedica.setDiagnosticoPaciente(paciente.getDiagnostico());
        visitaMedica.setTelefonoPaciente(paciente.getTelefono());//
        visitaMedica.setPaciente(paciente);// Relacionar visita con paciente
        return visitaMedicaService.guardarVisita(visitaMedica);
    }

    // Obtener visitas médicas de un paciente
    @GetMapping("/{pacienteId}/visitas")
    public List<VisitaMedica> obtenerVisitas(@PathVariable Long pacienteId) {
        return visitaMedicaService.obtenerVisitasPorPaciente(pacienteId);
    }

        //Eliminar paciente por ID
    @DeleteMapping("/{pacienteId}")
    public ResponseEntity<String> eliminarPacientePorId(@PathVariable Long pacienteId) {
        try {
            pacienteService.eliminarPacientePorId(pacienteId);
            return ResponseEntity.ok("Paciente con ID " + pacienteId + " eliminado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Eliminar paciente por nombre
    @DeleteMapping("/nombre/{nombre}")
    public ResponseEntity<String> eliminarPacientePorNombre(@PathVariable String nombre) {
        try {
            pacienteService.eliminarPacientePorNombre(nombre);
            return ResponseEntity.ok("Paciente con nombre " + nombre + " eliminado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

        //Actualizar parcialmente un paciente (PATCH)
        @PatchMapping("/{pacienteId}")
        public ResponseEntity<?> actualizarPacienteParcial(@PathVariable Long pacienteId, @RequestBody Paciente paciente) {
            try {
                Paciente pacienteActualizado = pacienteService.actualizarPacienteParcial(pacienteId, paciente);
                return ResponseEntity.ok(pacienteActualizado);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }
}