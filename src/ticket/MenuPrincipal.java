package ticket;
import java.util.Scanner;

public class MenuPrincipal {
    Scanner t = new Scanner(System.in);

    public void muestraMenu(){
        boolean salir = false;
        SistemaTicket sistemaTicket = new SistemaTicket(t);

        while (!salir) {
            mostrarOpciones();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> {
                    sistemaTicket.crearTicket();
                    salir = preguntarSiDeseaSalir(t);
                }
                case 2 -> {
                    sistemaTicket.listarTicket();
                    salir = preguntarSiDeseaSalir(t);
                }
                case 3 -> {
                    sistemaTicket.buscarTicketId();
                    salir = preguntarSiDeseaSalir(t);
                }
                case 4 -> {
                    sistemaTicket.cambiarEstadodeTicket();
                    salir = preguntarSiDeseaSalir(t);
                }
                case 5 -> {
                    sistemaTicket.cambiarPrioridadTicket();
                    salir = preguntarSiDeseaSalir(t);
                }
                case 6 -> {
                    sistemaTicket.mostrarEstadisticas();
                    salir = preguntarSiDeseaSalir(t);
                }case 7 ->{
                    sistemaTicket.eliminarTicket();
                    salir = preguntarSiDeseaSalir(t);
                }case 8 -> {
                    salir = true;
                    System.out.println(">> Saliendo del sistema. ¡Adios!");
                }
                default -> System.out.println(">> Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println("----------------------------------\n");
        }
        t.close();
    }

    private void mostrarOpciones(){
        System.out.print("""
                >> Operaciones que puedes realizar:
                \t1. Crear nuevo ticket
                \t2. Listar tickets
                \t3. Buscar ticket por Id
                \t4. Cambiar estado de ticket
                \t5. Cambiar prioridad del ticket
                \t6. Mostrar estadisticas ticket
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

    public static boolean preguntarSiDeseaSalir(Scanner teclado){
        System.out.println("¿Desea realizar otro proceso? (S/N): ");
        String respuesta = teclado.nextLine().trim().toUpperCase();

        if (respuesta.equals("N") || respuesta.equals("NO")){
            System.out.println(">> Saliendo del sistema. ¡Adios!");
            return true;
        }else {
            return false;
        }
    }


}
