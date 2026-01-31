import ticket.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        System.out.println("*** Sistema de Gestión de Tickets ***");

        MenuPrincipal menu = new MenuPrincipal();
        menu.muestraMenu();
        System.out.println("*** Aplicación finalizada. ***");
    }
}
 