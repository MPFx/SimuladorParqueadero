package principal;

import ui.MenuConsola;

/**
 * Clase principal que contiene el punto de entrada del Simulador de Parqueadero.
 * Inicia la aplicacion y muestra el menu de consola al usuario.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see MenuConsola
 */
public class Main {
    
    /**
     * Metodo principal que inicia el simulador de parqueadero.
     * Muestra mensaje de bienvenida y crea una instancia del menu de consola.
     * 
     * @param args Argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   BIENVENIDO AL SIMULADOR DE");
        System.out.println("         PARQUEADERO");
        System.out.println("========================================");
        System.out.println("Registra ingresos y salidas de vehículos,");
        System.out.println("controla espacios y genera reportes.");
        System.out.println("========================================\n");
        
        MenuConsola menu = new MenuConsola();
        menu.iniciar();
    }
    
}//fin de la clase