package com.hospital.seguimiento_pacientes.service;

import com.hospital.seguimiento_pacientes.model.Alerta;
import com.hospital.seguimiento_pacientes.model.Paciente;
import com.hospital.seguimiento_pacientes.model.VisitaMedica;
import com.hospital.seguimiento_pacientes.repository.AlertaRepository;
import com.hospital.seguimiento_pacientes.repository.PacienteRepository;
import com.hospital.seguimiento_pacientes.repository.VisitaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private VisitaMedicaRepository visitaMedicaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private SmsService smsService;

    @Value("${doctor.phone}")
    private String doctorPhoneNumber;

    // Verificar y generar alertas para un paciente
    public void generarAlertasParaPaciente(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Verificar si no hay visitas médicas recientes
        List<VisitaMedica> visitas = visitaMedicaRepository.findByPacienteId(pacienteId);
        if (visitas.isEmpty() || visitas.stream().noneMatch(visita -> visita.getFecha().after(new Date()))) {
            // Generar alerta por visita pendiente
            Alerta alerta = new Alerta(
                    "Visita Pendiente",
                    "El paciente " + paciente.getNombre() + " con diagnóstico " + paciente.getDiagnostico() +
                            " no ha tenido una visita médica en más de 15 días. Debe asistir a una consulta.",
                    paciente
            );
            alertaRepository.save(alerta);

            // Enviar el mensaje al teléfono del doctor
            smsService.enviarMensaje(doctorPhoneNumber,
                    "Paciente " + paciente.getNombre() + " con diagnóstico " + paciente.getDiagnostico() +
                            " debe asistir a una visita médica. Última consulta hace más de 15 días.");
        }
    }
}
