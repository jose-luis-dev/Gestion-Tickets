package ticket;

import java.time.LocalDateTime;

public class Ticket {
    private int idTicket;
    protected String titulo;
    protected String descripcion;
    private Estado estadoActual;
    private Prioridad prioridadActual;
    private final LocalDateTime fechaCreacion;

    public Ticket(int idTicket, String titulo, String descripcion){
        this.idTicket = idTicket;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estadoActual = Estado.ABIERTO;
        this.prioridadActual = Prioridad.ALTA;
        this.fechaCreacion = LocalDateTime.now();
    }

    public int getIdTicket() { return this.idTicket; }

    public String getTitulo() { return this.titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo;}

    public String getDescripcion() { return this.descripcion; }
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Estado getEstadoActual() { return this.estadoActual; }
    public void setEstadoActual(Estado estadoActual) { this.estadoActual = estadoActual; }

    public Prioridad getPrioridadActual() { return prioridadActual; }
    public void setPrioridadActual(Prioridad prioridadActual) { this.prioridadActual = prioridadActual;}

    public LocalDateTime getFechaCreacion() { return this.fechaCreacion; }


    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                "fechaCreacion=" + fechaCreacion +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estadoActual=" + estadoActual +
                ", prioridadActual=" + prioridadActual +
                '}';
    }

    public void mostrarDetalle(){
        System.out.printf("""
                -- Número del ticket : %d
                \tTitulo: %s
                \tFecha Creacion: %s
                \tDescripcion: %s
                \tEstado: %s
                \tPrioridad: %s
                -----------------------------
                """, getIdTicket(),getTitulo()
                ,getFechaCreacion().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
                ,getDescripcion(),getEstadoActual(),getPrioridadActual());
    }

}
