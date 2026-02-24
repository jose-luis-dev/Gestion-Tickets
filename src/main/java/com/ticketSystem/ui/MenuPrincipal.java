package com.ticketSystem.ui;

import com.ticketSystem.enums.EstadoOperacional;
import com.ticketSystem.enums.Prioridad;
import com.ticketSystem.enums.RolUsuario;
import com.ticketSystem.model.Ticket;
import com.ticketSystem.model.Usuario;
import com.ticketSystem.service.TicketService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuPrincipal {

    private Scanner t = new Scanner(System.in);
    private TicketService ticketService = new TicketService();

    public void muestraMenu(){
        boolean salir = false;
        while (!salir) {
            mostrarOpciones();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> crearTicketUI();
                case 2 -> listarTicketUI();
                case 3 -> buscarTicketUI();
                case 4 -> cambiarEstadoUI();
                case 5 -> cambiarPrioridadUI();
                case 6 -> mostrarEstadisticasUI();
                case 7 -> eliminarTicketUI();
                case 8 -> {
                    salir = true;
                    System.out.println(">> Saliendo del sistema.");
                }
                default -> System.out.println(">> Opción inválida");
            }
        }
        t.close();
    }


    // ---------------- UI METHODS ------------------

    private void crearTicketUI(){
        System.out.print("Titulo: ");
        String titulo = t.nextLine();

        System.out.print("Descripción: ");
        String descripcion = t.nextLine();

        Ticket ticket = ticketService.crearTicket(titulo, descripcion);
        System.out.println("Ticket creado con Id: " + ticket.getIdTicket());
    }

    private void listarTicketUI(){
        List<Ticket> tickets = ticketService.listarTicket();

        if (tickets.isEmpty()){
            System.out.println("No hay tickets.");
            return;
        }
        tickets.forEach(Ticket::mostrarDetalle);
    }

    private void buscarTicketUI(){
        System.out.print("ID del ticket: ");
        int id = Integer.parseInt(t.nextLine());

        Ticket ticket = ticketService.buscarTicketId(id);

        if (ticket == null){
            System.out.println("No encontrado.");
        }else {
            ticket.mostrarDetalle();
        }
    }

    private void cambiarEstadoUI(){
        System.out.print("ID del ticket: ");
        int id = Integer.parseInt(t.nextLine());

        System.out.print("Nuevo estado (ABIERTO, EN_PROCESO, CERRADO, CANCELADO): ");
        EstadoOperacional estado = EstadoOperacional.valueOf(t.nextLine().toUpperCase().replace(" ", "_"));

        boolean actualizado = ticketService.cambiarEstadodeTicket(id, estado);

        if (actualizado)
            System.out.println("Estado actualizado.");
        else
            System.out.println("Ticket no encontrado.");

    }

    private void cambiarPrioridadUI(){
        System.out.print("ID del ticket: ");
        int id = Integer.parseInt(t.nextLine());

        System.out.print("Nueva prioridad (ALTA, MEDIA, BAJA): ");
        Prioridad prioridad = Prioridad.valueOf(t.nextLine().toUpperCase());

        boolean actualizado = ticketService.cambiarPrioridadTicket(id, prioridad);

        if (actualizado)
            System.out.println("Prioridad actualizada.");
        else
            System.out.println("Ticket no encontrado.");
    }

    private void mostrarEstadisticasUI(){
        Map<String, Integer> stats = ticketService.obtenerEstadisticas();

        System.out.println("TOTAL: " + stats.get("TOTAL"));
        System.out.println("ABIERTOS: " + stats.get("ABIERTOS"));
        System.out.println("EN_PROCESO: " + stats.get("EN_PROCESO"));
        System.out.println("CERRADOS: " + stats.get("CERRADO"));
        System.out.println("CANCELADOS: " + stats.get("CANCELADOS"));
    }

    private void eliminarTicketUI(){
        Usuario usuarioActual = new Usuario(1, "Luis", "123456", RolUsuario.ADMIN);

        System.out.print("ID ticket a eliminar: ");
        int id = Integer.parseInt(t.nextLine());

        boolean eliminado = ticketService.eliminarTicket(id,usuarioActual);

        if (eliminado)
            System.out.println("Ticket eliminado lógicamente.");
        else
            System.out.println("No tienes permisos o ticket no existe.");
    }


    private void mostrarOpciones(){
        System.out.print("""
                \t1. Crear ticket
                \t2. Listar tickets
                \t3. Buscar ticket
                \t4. Cambiar estado
                \t5. Cambiar prioridad
                \t6. Estadísticas
                \t7. Eliminar Ticket
                \t8. Salir
                Escoje una opción:\s""");
    }

    private int leerOpcion() {
        int opcion = 0;
        try {
            opcion = Integer.parseInt(t.nextLine());
        }catch (NumberFormatException e){
            // Si el usuario ingresa texto en lugar de un número
            opcion = 0; // Valor inválido por defecto
        }
        return opcion;
    }
}
