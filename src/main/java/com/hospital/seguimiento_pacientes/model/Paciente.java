package com.hospital.seguimiento_pacientes.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String diagnostico;
    private LocalDate fechaNacimiento;
    private String telefono;

    @OneToMany(mappedBy = "paciente")
    private List<VisitaMedica> visitasMedicas;

    // Constructor vacío obligatorio para JPA
    public Paciente() {}

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<VisitaMedica> getVisitasMedicas() {
        return visitasMedicas;
    }

    public void setVisitasMedicas(List<VisitaMedica> visitasMedicas) {
        this.visitasMedicas = visitasMedicas;
    }

    // Campo calculado para la edad
    public int getEdad() {
        if (this.fechaNacimiento == null) {
            return 0; // Edad desconocida si no hay fecha de nacimiento
        }
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }
}