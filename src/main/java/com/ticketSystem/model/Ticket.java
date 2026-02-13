package com.ticketSystem.model;

import com.ticketSystem.enums.EstadoOperacional;
import com.ticketSystem.enums.EstadoRegistro;
import com.ticketSystem.enums.Prioridad;

import java.time.LocalDateTime;

public class Ticket {
    private int idTicket;
    protected String titulo;
    protected String descripcion;
    private EstadoOperacional estadoOperacionalActual;
    private Prioridad prioridadActual;
    private final LocalDateTime fechaCreacion;
    private EstadoRegistro estadoOperacionalTicket;

    public Ticket(int idTicket, String titulo, String descripcion){
        this.idTicket = idTicket;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoOperacionalActual = EstadoOperacional.ABIERTO;
        this.prioridadActual = Prioridad.ALTA;
        this.fechaCreacion = LocalDateTime.now();
        this.estadoOperacionalTicket = EstadoRegistro.ACTIVO;
    }

    public int getIdTicket() { return this.idTicket; }

    public String getTitulo() { return this.titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo;}

    public String getDescripcion() { return this.descripcion; }
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Prioridad getPrioridadActual() { return prioridadActual; }
    public void setPrioridadActual(Prioridad prioridadActual) { this.prioridadActual = prioridadActual;}

    public EstadoOperacional getEstadoOperacionalActual() { return this.estadoOperacionalActual; }
    public void setEstadoOperacionalActual(EstadoOperacional estadoOperacionalActual) { this.estadoOperacionalActual = estadoOperacionalActual; }

    public EstadoRegistro getEstadoOperacionalTicket() { return this.estadoOperacionalTicket; }
    public void setEstadoOperacionalTicket(EstadoRegistro estadoOperacionalTicket) { this.estadoOperacionalTicket = estadoOperacionalTicket;}

    public LocalDateTime getFechaCreacion() { return this.fechaCreacion; }


    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                "fechaCreacion=" + fechaCreacion +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estadoActual=" + estadoOperacionalActual +
                ", prioridadActual=" + prioridadActual +
                ", estadoTicket=" + estadoOperacionalTicket +
                '}';
    }

    public void mostrarDetalle(){
        System.out.printf("""
                -- Número del ticket : %d
                \tTitulo: %s
                \tFecha Creacion: %s
                \tDescripcion: %s
                \tEstado Actual: %s
                \tPrioridad: %s
                \tEstado Ticket: %s
                -----------------------------
                """, getIdTicket(),getTitulo()
                ,getFechaCreacion().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
                ,getDescripcion(),getEstadoOperacionalActual(),getPrioridadActual(),getEstadoOperacionalTicket());
    }

}
