package service;

import model.Parqueadero;
import model.Vehiculo;
import model.Carro;
import model.Moto;
import model.Camion;
import model.Bicicleta;
import model.Ticket;

import java.util.Scanner;

/**
 * Clase de servicio que gestiona las operaciones del parqueadero.
 * Permite registrar ingresos, salidas y consultar el estado del parqueadero.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Parqueadero
 * @see Vehiculo
 * @see Ticket
 */
public class ParqueaderoService {
    
    // ==================== ATRIBUTOS ====================
    
    private Parqueadero parqueadero;
    
    /**
     * Constructor del servicio de parqueadero.
     * 
     * @param parqueadero Parqueadero a gestionar
     */
    public ParqueaderoService(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }
    
    /**
     * Registra el ingreso de un vehiculo.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void registrarIngreso(Scanner scanner) {
        System.out.println("\n=== REGISTRO DE INGRESO ===");
        
        System.out.println("Tipo de vehículo:");
        System.out.println("1. Carro ($1000/hora)");
        System.out.println("2. Moto ($500/hora)");
        System.out.println("3. Camión ($2000/hora)");
        System.out.println("4. Bicicleta ($200/hora)");
        System.out.print("Seleccione: ");
        
        int tipo;
        try {
            tipo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            tipo = 0;
        }
        
        System.out.print("Placa: ");
        String placa = scanner.nextLine().toUpperCase();
        
        Vehiculo vehiculo = switch (tipo) {
            case 2 -> new Moto(placa);
            case 3 -> new Camion(placa);
            case 4 -> new Bicicleta(placa);
            default -> new Carro(placa);
        };
        
        Ticket ticket = parqueadero.registrarIngreso(vehiculo);
        if (ticket != null) {
            System.out.println("\n✅ Ingreso registrado!");
            System.out.println("Ticket #" + ticket.getIdTicket());
            System.out.println("Vehículo: " + vehiculo.getPlaca() + " (" + vehiculo.getTipo() + ")");
            System.out.println("Hora de ingreso: " + vehiculo.getHoraIngreso());
        }
    }
    
    /**
     * Registra la salida de un vehiculo.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void registrarSalida(Scanner scanner) {
        System.out.println("\n=== REGISTRO DE SALIDA ===");
        
        mostrarTicketsActivos();
        
        System.out.print("\nNúmero de ticket: ");
        int idTicket;
        try {
            idTicket = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido");
            return;
        }
        
        if (parqueadero.registrarSalida(idTicket)) {
            Ticket ticket = parqueadero.getTickets().stream()
                    .filter(t -> t.getIdTicket() == idTicket)
                    .findFirst()
                    .orElse(null);
            
            if (ticket != null) {
                System.out.println("\n✅ Salida registrada!");
                System.out.println("Vehículo: " + ticket.getVehiculo().getPlaca());
                System.out.println("Horas de estadía: " + ticket.getHorasEstadia());
                System.out.println("Valor a pagar: $" + ticket.getValorPagado());
            }
        }
    }
    
    /**
     * Muestra los tickets activos (vehículos dentro del parqueadero).
     */
    public void mostrarTicketsActivos() {
        System.out.println("\n=== VEHÍCULOS EN EL PARQUEADERO ===");
        
        var activos = parqueadero.getTickets().stream()
                .filter(t -> t.getHoraSalida() == null)
                .toList();
        
        if (activos.isEmpty()) {
            System.out.println("No hay vehículos en el parqueadero");
            return;
        }
        
        for (Ticket t : activos) {
            System.out.println(t);
        }
    }
    
    /**
     * Muestra el estado actual del parqueadero.
     */
    public void mostrarEstado() {
        parqueadero.mostrarEstado();
    }
    
    /**
     * Muestra los ingresos totales del parqueadero.
     */
    public void mostrarIngresos() {
        System.out.println("\n=== INGRESOS TOTALES ===");
        System.out.printf("Total recaudado: $%.2f%n", parqueadero.getIngresosTotales());
    }
    
    /**
     * Obtiene el parqueadero.
     * 
     * @return Parqueadero
     */
    public Parqueadero getParqueadero() {
        return parqueadero;
    }
    
}//fin de la clase