package com.hospital.seguimiento_pacientes.model;

import jakarta.persistence.*;


import java.util.Date;

@Entity
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    // Constructor vacío obligatorio para JPA
    public Alerta() {}

    // Constructor con parámetros
    public Alerta(String titulo, String mensaje, Paciente paciente) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = new Date();
        this.paciente = paciente;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
