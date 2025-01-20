package com.hospital.seguimiento_pacientes.controller;

import com.hospital.seguimiento_pacientes.model.Alerta;
import com.hospital.seguimiento_pacientes.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @PostMapping
    public Alerta generarAlerta(@RequestBody Alerta alerta) {
        return alertaService.generarAlerta(alerta);
    }
}
