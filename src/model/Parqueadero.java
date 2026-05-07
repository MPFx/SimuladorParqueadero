package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que representa el parqueadero completo.
 * Gestiona los espacios disponibles y los tickets generados.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see EspacioParqueadero
 * @see Ticket
 * @see Vehiculo
 */
public class Parqueadero {
    
    // ==================== ATRIBUTOS ====================
    
    /** Nombre del parqueadero. */
    private String nombre;
    
    /** Numero de pisos del parqueadero. */
    private int pisos;
    
    /** Lista de espacios del parqueadero. */
    private List<EspacioParqueadero> espacios;
    
    /** Lista de tickets generados. */
    private List<Ticket> tickets;
    
    /** Ingresos totales acumulados. */
    private double ingresosTotales;
    
    /**
     * Constructor para crear un parqueadero.
     * 
     * @param nombre Nombre del parqueadero
     * @param pisos Numero de pisos
     */
    public Parqueadero(String nombre, int pisos) {
        this.nombre = nombre;
        this.pisos = pisos;
        this.espacios = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.ingresosTotales = 0;
        inicializarEspacios();
    }
    
    /**
     * Inicializa los espacios del parqueadero.
     * Por piso: 20 espacios (10 carros, 5 motos, 3 camiones, 2 bicicletas)
     */
    private void inicializarEspacios() {
        for (int p = 1; p <= pisos; p++) {
            // 10 espacios para carros
            for (int i = 1; i <= 10; i++) {
                espacios.add(new EspacioParqueadero(p, i, "Carro"));
            }
            // 5 espacios para motos
            for (int i = 11; i <= 15; i++) {
                espacios.add(new EspacioParqueadero(p, i, "Moto"));
            }
            // 3 espacios para camiones
            for (int i = 16; i <= 18; i++) {
                espacios.add(new EspacioParqueadero(p, i, "Camion"));
            }
            // 2 espacios para bicicletas
            for (int i = 19; i <= 20; i++) {
                espacios.add(new EspacioParqueadero(p, i, "Bicicleta"));
            }
        }
    }
    
    /**
     * Busca un espacio disponible para un tipo de vehiculo.
     * 
     * @param tipoVehiculo Tipo de vehiculo
     * @return Espacio disponible o null
     */
    public EspacioParqueadero buscarEspacioDisponible(String tipoVehiculo) {
        return espacios.stream()
                .filter(e -> e.getTipoVehiculo().equals(tipoVehiculo) && !e.isOcupado())
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Registra el ingreso de un vehiculo.
     * 
     * @param vehiculo Vehiculo que ingresa
     * @return Ticket generado o null si no hay espacio
     */
    public Ticket registrarIngreso(Vehiculo vehiculo) {
        EspacioParqueadero espacio = buscarEspacioDisponible(vehiculo.getTipo());
        if (espacio == null) {
            System.out.println("No hay espacios disponibles para " + vehiculo.getTipo());
            return null;
        }
        
        espacio.setOcupado(true);
        vehiculo.setHoraIngreso(java.time.LocalDateTime.now());
        
        Ticket ticket = switch (vehiculo.getTipo()) {
            case "Carro" -> new TicketCarro(vehiculo);
            case "Moto" -> new TicketMoto(vehiculo);
            case "Camion" -> new TicketCamion(vehiculo);
            default -> new TicketBici(vehiculo);
        };
        
        tickets.add(ticket);
        return ticket;
    }
    
    /**
     * Registra la salida de un vehiculo.
     * 
     * @param idTicket Identificador del ticket
     * @return true si se registro correctamente
     */
    public boolean registrarSalida(int idTicket) {
        Ticket ticket = tickets.stream()
                .filter(t -> t.getIdTicket() == idTicket && t.getHoraSalida() == null)
                .findFirst()
                .orElse(null);
        
        if (ticket == null) {
            System.out.println("Ticket no encontrado o ya finalizado");
            return false;
        }
        
        ticket.registrarSalida();
        ingresosTotales += ticket.getValorPagado();
        
        // Liberar espacio
        EspacioParqueadero espacio = espacios.stream()
                .filter(e -> e.getTipoVehiculo().equals(ticket.getVehiculo().getTipo()) && e.isOcupado())
                .findFirst()
                .orElse(null);
        if (espacio != null) {
            espacio.setOcupado(false);
        }
        
        return true;
    }
    
    /**
     * Obtiene la cantidad de espacios disponibles.
     * 
     * @return Numero de espacios disponibles
     */
    public int espaciosDisponibles() {
        return (int) espacios.stream().filter(e -> !e.isOcupado()).count();
    }
    
    /**
     * Muestra el estado actual del parqueadero.
     */
    public void mostrarEstado() {
        System.out.println("\n=== ESTADO DEL PARQUEADERO ===");
        System.out.println("Total espacios: " + espacios.size());
        System.out.println("Espacios disponibles: " + espaciosDisponibles());
        System.out.println("Ingresos totales: $" + ingresosTotales);
        
        for (int p = 1; p <= pisos; p++) {
            System.out.println("\nPiso " + p + ":");
            final int piso = p;
            List<EspacioParqueadero> espaciosPiso = espacios.stream()
                    .filter(e -> e.getPiso() == piso)
                    .toList();
            
            long carrosOcupados = espaciosPiso.stream()
                    .filter(e -> e.getTipoVehiculo().equals("Carro") && e.isOcupado()).count();
            long carrosTotales = espaciosPiso.stream()
                    .filter(e -> e.getTipoVehiculo().equals("Carro")).count();
            
            System.out.printf("  Carros: %d/%d ocupados%n", carrosOcupados, carrosTotales);
            
            long motosOcupados = espaciosPiso.stream()
                    .filter(e -> e.getTipoVehiculo().equals("Moto") && e.isOcupado()).count();
            long motosTotales = espaciosPiso.stream()
                    .filter(e -> e.getTipoVehiculo().equals("Moto")).count();
            
            System.out.printf("  Motos: %d/%d ocupados%n", motosOcupados, motosTotales);
            
            long camionesOcupados = espaciosPiso.stream()
                    .filter(e -> e.getTipoVehiculo().equals("Camion") && e.isOcupado()).count();
            long camionesTotales = espaciosPiso.stream()
                    .filter(e -> e.getTipoVehiculo().equals("Camion")).count();
            
            System.out.printf("  Camiones: %d/%d ocupados%n", camionesOcupados, camionesTotales);
            
            long bicisOcupados = espaciosPiso.stream()
                    .filter(e -> e.getTipoVehiculo().equals("Bicicleta") && e.isOcupado()).count();
            long bicisTotales = espaciosPiso.stream()
                    .filter(e -> e.getTipoVehiculo().equals("Bicicleta")).count();
            
            System.out.printf("  Bicicletas: %d/%d ocupados%n", bicisOcupados, bicisTotales);
        }
    }
    
    // ==================== GETTERS ====================
    
    /** @return Nombre del parqueadero */
    public String getNombre() {
        return nombre;
    }
    
    /** @return Numero de pisos */
    public int getPisos() {
        return pisos;
    }
    
    /** @return Lista de espacios */
    public List<EspacioParqueadero> getEspacios() {
        return espacios;
    }
    
    /** @return Lista de tickets */
    public List<Ticket> getTickets() {
        return tickets;
    }
    
    /** @return Ingresos totales */
    public double getIngresosTotales() {
        return ingresosTotales;
    }
    
    /**
     * Devuelve una representacion textual del parqueadero.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return nombre + " - " + pisos + " pisos, " + espacios.size() + " espacios, $" + ingresosTotales;
    }
    
}//fin de la clase