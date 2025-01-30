package com.hospital.seguimiento_pacientes.controller;

import com.hospital.seguimiento_pacientes.model.Alerta;
import com.hospital.seguimiento_pacientes.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    // Endpoint para generar alerta manual con mensaje personalizado
    @PostMapping("/generar/{pacienteId}")
    public ResponseEntity<String> generarAlerta(@PathVariable Long pacienteId, @RequestBody String mensajePersonalizado) {
        alertaService.generarAlertasParaPaciente(pacienteId, mensajePersonalizado);
        return ResponseEntity.ok("Alerta generada y mensaje enviado");
    }
}
