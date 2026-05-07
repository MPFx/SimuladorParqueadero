package service;

import model.Parqueadero;
import model.Ticket;
import model.Vehiculo;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase de servicio que genera reportes del parqueadero.
 * Permite generar reportes diarios, por tipo de vehiculo y de ingresos.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Parqueadero
 * @see Ticket
 */
public class ReporteService {
    
    // ==================== ATRIBUTOS ====================
    
    private Parqueadero parqueadero;
    
    /**
     * Constructor del servicio de reportes.
     * 
     * @param parqueadero Parqueadero a analizar
     */
    public ReporteService(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }
    
    /**
     * Genera un reporte completo del parqueadero.
     */
    public void generarReporteCompleto() {
        System.out.println("\n=== REPORTE COMPLETO DEL PARQUEADERO ===");
        System.out.println("========================================");
        
        List<Ticket> tickets = parqueadero.getTickets();
        List<Ticket> finalizados = tickets.stream()
                .filter(t -> t.getHoraSalida() != null)
                .toList();
        
        System.out.println("Nombre: " + parqueadero.getNombre());
        System.out.println("Pisos: " + parqueadero.getPisos());
        System.out.println("Total espacios: " + parqueadero.getEspacios().size());
        System.out.println("Espacios disponibles: " + parqueadero.espaciosDisponibles());
        System.out.println("Total tickets generados: " + tickets.size());
        System.out.println("Total salidas registradas: " + finalizados.size());
        System.out.printf("Ingresos totales: $%.2f%n", parqueadero.getIngresosTotales());
        
        if (!finalizados.isEmpty()) {
            double promedio = finalizados.stream()
                    .mapToDouble(t -> t.getValorPagado())
                    .average()
                    .orElse(0);
            System.out.printf("Promedio por vehículo: $%.2f%n", promedio);
        }
        
        System.out.println("========================================");
    }
    
    /**
     * Genera un reporte por tipo de vehículo.
     */
    public void reportePorTipoVehiculo() {
        System.out.println("\n=== REPORTE POR TIPO DE VEHÍCULO ===");
        System.out.println("======================================");
        
        List<Ticket> finalizados = parqueadero.getTickets().stream()
                .filter(t -> t.getHoraSalida() != null)
                .toList();
        
        Map<String, Long> conteo = finalizados.stream()
                .collect(Collectors.groupingBy(
                    t -> t.getVehiculo().getTipo(),
                    Collectors.counting()
                ));
        
        Map<String, Double> ingresos = finalizados.stream()
                .collect(Collectors.groupingBy(
                    t -> t.getVehiculo().getTipo(),
                    Collectors.summingDouble(Ticket::getValorPagado)
                ));
        
        String[] tipos = {"Carro", "Moto", "Camion", "Bicicleta"};
        for (String tipo : tipos) {
            long cantidad = conteo.getOrDefault(tipo, 0L);
            double total = ingresos.getOrDefault(tipo, 0.0);
            System.out.printf("%-10s: %d vehículos - Total: $%.2f%n", tipo, cantidad, total);
        }
        
        System.out.println("======================================");
    }
    
    /**
     * Muestra el historial completo de tickets.
     */
    public void mostrarHistorialTickets() {
        System.out.println("\n=== HISTORIAL DE TICKETS ===");
        System.out.println("=============================");
        
        List<Ticket> tickets = parqueadero.getTickets();
        
        if (tickets.isEmpty()) {
            System.out.println("No hay tickets registrados");
            return;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (Ticket t : tickets) {
            String estado = (t.getHoraSalida() == null) ? "ACTIVO" : "FINALIZADO";
            System.out.printf("Ticket #%d | %s | %s | %s | ", 
                t.getIdTicket(), 
                t.getVehiculo().getPlaca(),
                t.getVehiculo().getTipo(),
                estado);
            
            if (t.getHoraSalida() != null) {
                System.out.printf("$%.2f%n", t.getValorPagado());
            } else {
                System.out.println("N/A");
            }
        }
        
        System.out.println("=============================");
    }
    
    /**
     * Muestra el resumen de ocupación por piso.
     */
    public void mostrarResumenOcupacion() {
        System.out.println("\n=== RESUMEN DE OCUPACIÓN POR PISO ===");
        System.out.println("=====================================");
        
        for (int p = 1; p <= parqueadero.getPisos(); p++) {
            final int piso = p;
            var espaciosPiso = parqueadero.getEspacios().stream()
                    .filter(e -> e.getPiso() == piso)
                    .toList();
            
            long ocupados = espaciosPiso.stream().filter(e -> e.isOcupado()).count();
            int total = espaciosPiso.size();
            double porcentaje = (total > 0) ? (double) ocupados / total * 100 : 0;
            
            System.out.printf("Piso %d: %d/%d espacios ocupados (%.1f%%)%n", 
                p, ocupados, total, porcentaje);
        }
        
        System.out.println("=====================================");
    }
    
}//fin de la clase