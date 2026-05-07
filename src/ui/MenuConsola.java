package ui;

import service.ParqueaderoService;
import service.ReporteService;
import model.Parqueadero;

import java.util.Scanner;

/**
 * Clase que implementa la interfaz de usuario por consola para el Simulador de Parqueadero.
 * Gestiona la interaccion con el usuario, muestra los menus y procesa las opciones.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see ParqueaderoService
 * @see ReporteService
 * @see Parqueadero
 */
public class MenuConsola {
    
    // ==================== ATRIBUTOS ====================
    
    private Scanner scanner;
    private ParqueaderoService parqueaderoService;
    private ReporteService reporteService;
    
    /**
     * Constructor del menu de consola.
     * Inicializa el scanner, el parqueadero y los servicios.
     */
    public MenuConsola() {
        this.scanner = new Scanner(System.in);
        Parqueadero parqueadero = new Parqueadero("Parqueadero Central", 3);
        this.parqueaderoService = new ParqueaderoService(parqueadero);
        this.reporteService = new ReporteService(parqueadero);
    }
    
    /**
     * Inicia el bucle principal del menu.
     */
    public void iniciar() {
        boolean salir = false;
        
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1 -> parqueaderoService.registrarIngreso(scanner);
                case 2 -> parqueaderoService.registrarSalida(scanner);
                case 3 -> parqueaderoService.mostrarEstado();
                case 4 -> parqueaderoService.mostrarTicketsActivos();
                case 5 -> parqueaderoService.mostrarIngresos();
                case 6 -> reporteService.generarReporteCompleto();
                case 7 -> reporteService.reportePorTipoVehiculo();
                case 8 -> reporteService.mostrarHistorialTickets();
                case 9 -> reporteService.mostrarResumenOcupacion();
                case 10 -> {
                    System.out.println("\n¡Gracias por usar el Simulador de Parqueadero!");
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                }
                default -> System.out.println("Opcion no valida");
            }
            
            if (!salir) {
                pausa();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el menu principal del sistema.
     */
    private void mostrarMenuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("     SIMULADOR DE PARQUEADERO");
        System.out.println("========================================");
        System.out.println("1. Registrar ingreso");
        System.out.println("2. Registrar salida");
        System.out.println("3. Ver estado del parqueadero");
        System.out.println("4. Ver vehículos estacionados");
        System.out.println("5. Ver ingresos totales");
        System.out.println("6. Reporte completo");
        System.out.println("7. Reporte por tipo de vehículo");
        System.out.println("8. Historial de tickets");
        System.out.println("9. Resumen de ocupación por piso");
        System.out.println("10. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
    }
    
    /**
     * Lee y valida la opcion ingresada por el usuario.
     * 
     * @return Numero entero de la opcion seleccionada, o 0 si no es valida
     */
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * Pausa la ejecucion hasta que el usuario presione Enter.
     */
    private void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
}//fin de la clase