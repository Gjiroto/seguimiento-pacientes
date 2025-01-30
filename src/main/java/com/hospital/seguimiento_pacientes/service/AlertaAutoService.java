package com.hospital.seguimiento_pacientes.service;

import com.hospital.seguimiento_pacientes.model.Alerta;
import com.hospital.seguimiento_pacientes.model.Paciente;
import com.hospital.seguimiento_pacientes.model.VisitaMedica;
import com.hospital.seguimiento_pacientes.repository.AlertaRepository;
import com.hospital.seguimiento_pacientes.repository.PacienteRepository;
import com.hospital.seguimiento_pacientes.repository.VisitaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlertaAutoService {

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

    // Método programado para ejecutar cada día a las 00:00
    @Scheduled(cron = "0 0 0 * * ?")  // Se ejecuta todos los días a la medianoche
    public void generarAlertasAutomatizadas() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        for (Paciente paciente : pacientes) {
            // Verificar si el paciente tiene visitas médicas
            List<VisitaMedica> visitas = visitaMedicaRepository.findByPacienteId(paciente.getId());
            if (!visitas.isEmpty()) {
                // Verificar la última visita
                VisitaMedica ultimaVisita = visitas.get(visitas.size() - 1);  // Última visita
                long diferenciaDias = (new Date().getTime() - ultimaVisita.getFecha().getTime()) / (1000 * 60 * 60 * 24);

                if (diferenciaDias > 15) {
                    // Generar alerta si han pasado más de 15 días desde la última visita
                    Alerta alerta = new Alerta(
                            "Visita Pendiente",
                            "El paciente " + paciente.getNombre() + " con diagnóstico " + paciente.getDiagnostico() +
                                    " no ha tenido una visita médica en más de 15 días. Debe asistir a una consulta.",
                            paciente
                    );
                    alertaRepository.save(alerta);

                    // Enviar mensaje al doctor
                    smsService.enviarMensaje(doctorPhoneNumber,
                            "Paciente " + paciente.getNombre() + " con diagnóstico " + paciente.getDiagnostico() +
                                    " debe asistir a una visita médica. Última consulta hace más de 15 días.");
                }
            }
        }
    }
}
