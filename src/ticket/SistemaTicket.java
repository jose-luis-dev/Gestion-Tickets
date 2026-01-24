package ticket;

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
        System.out.print("Ingrese el Id del ticket a buscar: ");
        int idBusqueda = t.nextInt();
        t.nextLine();

        Ticket ticketEncontrado = obtenerTicketPorId(idBusqueda);

        if (ticketEncontrado != null){
            ticketEncontrado.mostrarDetalle(); // Llamando al metodo mostrarDetalle
        }else {
            System.out.println("No se encontró ningún ticket con el ID: " +  idBusqueda);
        }
    }
    // Cambiar el estado del ticket setEstadoActual ABIERTO, EN_PROCESO, FINALIZADO
    public void cambiarEstadodeTicket(){
        System.out.print("Ingrese el Id del ticket a buscar: ");
        int idBuscado = t.nextInt();
        t.nextLine();

        Ticket ticketEncontrado = obtenerTicketPorId(idBuscado);

        if (ticketEncontrado !=null){
            System.out.println("Estado Actual del ticket: " + ticketEncontrado.getEstadoActual());
            System.out.print("Selecciona nuevo estado del ticket (ABIERTO, EN_PROCESO, CERRADO): ");
            String nuevoEstadoStr = t.nextLine().toUpperCase().replace(" ", "_");

            try {
                // Convertir la cadena ingresada a un valor del enum
                Estado estado = Estado.valueOf(nuevoEstadoStr);
                ticketEncontrado.setEstadoActual(estado); // Modificamos el valor
                System.out.println("Estado del ticket actualizado correctamente.");
            } catch (IllegalArgumentException e){
                System.out.println("Estado del ticket invalido. Intente de nuevo.");
            }
        } else {
            System.out.println("No se encontró ningún ticket con el ID: " + idBuscado);
        }
    }
    // Cambiar la prioridad del ticket setPrioridadActual ALTA, MEDIA, BAJA
    public void cambiarPrioridadTicket(){
        System.out.print("Ingrese el Id del ticket a buscar: ");
        int idBuscado = t.nextInt();
        t.nextLine();

        Ticket ticketEncontrado = obtenerTicketPorId(idBuscado);

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
            System.out.println("No se encontró ningún ticket con el ID: " + idBuscado);
        }
    }
    // Mostrar estadisticas de los tickets
    public void mostrarEstadisticas(){
        int totalTickets = listaTicket.size();

        int ticketAbierto = 0;
        int ticketEnProceso = 0;
        int ticketCerrado = 0;

        for (Ticket ticket : listaTicket){
            if (ticket.getEstadoActual() == Estado.ABIERTO){
                ticketAbierto++;
            } else if (ticket.getEstadoActual() == Estado.EN_PROCESO) {
                ticketEnProceso++;
            } else if (ticket.getEstadoActual() == Estado.CERRADO) {
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
