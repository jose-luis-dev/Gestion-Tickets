package com.ticketSystem.service;

import com.ticketSystem.enums.EstadoOperacional;
import com.ticketSystem.enums.EstadoRegistro;
import com.ticketSystem.enums.Prioridad;
import com.ticketSystem.enums.RolUsuario;
import com.ticketSystem.model.Ticket;
import com.ticketSystem.model.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaTicket {
    // Almacenar ticket desde un ArrayList
    private ArrayList<Ticket> listaTicket = new ArrayList<>();
    private int idTicket = 1;
    private Scanner t;
    public SistemaTicket(Scanner scanner){ this.t = scanner; }
    // Creacion de ticket
    public void crearTicket(){
        System.out.print("Ingrese el titulo del ticket: ");
        String titulo = t.nextLine();
        System.out.print("Ingrese la descripción del ticket: ");
        String descripcion = t.nextLine();
        // Se crea una instancia de objeto Ticket para los datos ingresados.
        Ticket nuevoTicket = new Ticket(idTicket++, titulo, descripcion);

        //Se agrega al ArrayList
        listaTicket.add(nuevoTicket);
        System.out.println("Ticket #" + nuevoTicket.getIdTicket() + " Creado exitosamente.");
    }
    // Mostrar la lista de tickets
    public void listarTicket(){
        if (listaTicket.isEmpty()){
            System.out.println("No hay tickets registrados.");
            return;
        }
        System.out.println("*** Lista de Tickets ***");
        for (Ticket list : listaTicket){
            list.mostrarDetalle();
        }
    }
    //Metodo para captura Id ticket
    private int capturaPorId(){
        System.out.println("Busqueda de ticket por id");
        System.out.print("Ingrese el Id del ticket a buscar: ");
        int idBusqueda = t.nextInt();
        t.nextLine();
        return idBusqueda;
    }
    // Metodo para obtener el id del ticket
    private Ticket obtenerTicketPorId(int idTicket){
        for (Ticket ticket : listaTicket){ // Iterar sobre la listaTicket
            if (ticket.getIdTicket() == idTicket){ // Comparar el Id
                if (ticket.getIdTicket() == idTicket){
                    return ticket;
                }
            }
        }
        return null;
    }
    // Busqueda por Id del ticket
    public void buscarTicketId(){
        int idABuscar = capturaPorId();

        Ticket ticketEncontrado = obtenerTicketPorId(idABuscar);

        if (ticketEncontrado != null){
            ticketEncontrado.mostrarDetalle(); // Llamando al metodo mostrarDetalle
        }else {
            System.out.println("No se encontró ningún ticket con el ID: " +  idABuscar);
        }
    }
    // Cambiar el estado del ticket setEstadoActual ABIERTO, EN_PROCESO, FINALIZADO
    public void cambiarEstadodeTicket(){
        int idABuscar = capturaPorId();
        Ticket ticketEncontrado = obtenerTicketPorId(idABuscar);

        if (ticketEncontrado !=null){
            System.out.println("Estado Actual del ticket: " + ticketEncontrado.getEstadoOperacionalActual());
            System.out.print("Selecciona nuevo estado del ticket (ABIERTO, EN_PROCESO, CERRADO, CANCELADO): ");
            String nuevoEstadoStr = t.nextLine().toUpperCase().replace(" ", "_");

            try {
                // Convertir la cadena ingresada a un valor del enum
                EstadoOperacional estadoOperacional = EstadoOperacional.valueOf(nuevoEstadoStr);
                ticketEncontrado.setEstadoOperacionalActual(estadoOperacional); // Modificamos el valor
                System.out.println("Estado del ticket actualizado correctamente.");
            } catch (IllegalArgumentException e){
                System.out.println("Estado del ticket invalido. Intente de nuevo.");
            }
        } else {
            System.out.println("No se encontró ningún ticket con el ID: " + idABuscar);
        }
    }
    // Cambiar la prioridad del ticket setPrioridadActual ALTA, MEDIA, BAJA
    public void cambiarPrioridadTicket(){
        int idABuscar = capturaPorId();
        Ticket ticketEncontrado = obtenerTicketPorId(idABuscar);

        if (ticketEncontrado !=null){
            System.out.println("Prioridad Actual del ticket: " + ticketEncontrado.getPrioridadActual());
            System.out.print("Selecciona nuevo estado del ticket (ALTA, MEDIA, BAJA): ");
            String nuevaPrioridadStr = t.nextLine().toUpperCase().replace(" ", "_");

            try {
                // Convertir la cadena ingresada a un valor del enum
                Prioridad prioridad = Prioridad.valueOf(nuevaPrioridadStr);
                ticketEncontrado.setPrioridadActual(prioridad); // Modificamos el valor
                System.out.println("Prioridad del ticket actualizado correctamente.");
            } catch (IllegalArgumentException e){
                System.out.println("Prioridad del ticket invalido. Intente de nuevo.");
            }
        } else {
            System.out.println("No se encontró ningún ticket con el ID: " + idABuscar);
        }
    }
    // Eliminar el ticket "Solo ADMIN"
    public void eliminarTicket(Usuario usuarioActual){
        if (usuarioActual.getRol() != RolUsuario.ADMIN){
            System.out.println("No tienes permisos para eliminar tickets.");
            return;
        }
        int idABuscar = capturaPorId();
        Ticket ticketEncontrado = obtenerTicketPorId(idABuscar);
        if (ticketEncontrado != null){
            System.out.println("Se elimino ticket con exito...");
            ticketEncontrado.setEstadoOperacionalTicket(EstadoRegistro.INACTIVO);
        } else {
            System.out.println("No se encontró ningún ticket con el Id: " +  idABuscar);
        }
    }
    // Mostrar estadisticas de los tickets
    public void mostrarEstadisticas(){
        int totalTickets = listaTicket.size();

        int ticketAbierto = 0;
        int ticketEnProceso = 0;
        int ticketCerrado = 0;

        for (Ticket ticket : listaTicket){
            if (ticket.getEstadoOperacionalActual() == EstadoOperacional.ABIERTO){
                ticketAbierto++;
            } else if (ticket.getEstadoOperacionalActual() == EstadoOperacional.EN_PROCESO) {
                ticketEnProceso++;
            } else if (ticket.getEstadoOperacionalActual() == EstadoOperacional.CERRADO) {
                ticketCerrado++;
            }
        }

        System.out.printf("""
                ---- Estadisticas de Tickets -----
                Total de tickets creados: %d
                Tickets Abiertos: %d
                Tickets En proceso: %d
                Tickets Cerrados: %d
                -----------------------------------
                """, totalTickets, ticketAbierto, ticketEnProceso, ticketCerrado);
    }
}
