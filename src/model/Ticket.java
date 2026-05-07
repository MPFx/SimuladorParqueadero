package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Clase abstracta que representa un ticket de parqueadero.
 * Contiene la informacion del vehiculo, horarios y valor a pagar.
 * Implementa la interfaz Tarifa.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see TicketCarro
 * @see TicketMoto
 * @see TicketCamion
 * @see TicketBici
 * @see Tarifa
 */
public abstract class Ticket implements Tarifa {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico del ticket. */
    protected int idTicket;
    
    /** Vehiculo asociado al ticket. */
    protected Vehiculo vehiculo;
    
    /** Hora de ingreso del vehiculo. */
    protected LocalDateTime horaIngreso;
    
    /** Hora de salida del vehiculo. */
    protected LocalDateTime horaSalida;
    
    /** Valor pagado por la estadia. */
    protected double valorPagado;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear un ticket.
     * 
     * @param vehiculo Vehiculo que ingresa
     */
    public Ticket(Vehiculo vehiculo) {
        this.idTicket = contadorIds++;
        this.vehiculo = vehiculo;
        this.horaIngreso = vehiculo.getHoraIngreso();
        this.valorPagado = 0;
    }
    
    /**
     * Registra la salida del vehiculo y calcula el valor a pagar.
     */
    public void registrarSalida() {
        this.horaSalida = LocalDateTime.now();
        double horas = getHorasEstadia();
        this.valorPagado = calcularTarifa(horas);
    }
    
    /**
     * Calcula la tarifa segun las horas de estadia.
     * 
     * @param horas Numero de horas
     * @return Monto a pagar
     */
    @Override
    public double calcularTarifa(double horas) {
        double tarifaPorHora = vehiculo.getTarifaPorHora();
        double total = tarifaPorHora * Math.ceil(horas);
        // Redondear a 50 pesos mas cercano
        return Math.ceil(total / 50) * 50;
    }
    
    /**
     * Obtiene las horas de estadia redondeadas hacia arriba.
     * 
     * @return Horas de estadia (1 hora minima)
     */
    public double getHorasEstadia() {
        if (horaSalida == null) {
            return 0;
        }
        long minutos = ChronoUnit.MINUTES.between(horaIngreso, horaSalida);
        double horas = minutos / 60.0;
        return Math.max(1, Math.ceil(horas));
    }
    
    // ==================== GETTERS ====================
    
    /** @return Identificador del ticket */
    public int getIdTicket() {
        return idTicket;
    }
    
    /** @return Vehiculo asociado */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    
    /** @return Hora de ingreso */
    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }
    
    /** @return Hora de salida */
    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }
    
    /** @return Valor pagado */
    public double getValorPagado() {
        return valorPagado;
    }
    
    /**
     * Devuelve una representacion textual del ticket.
     * 
     * @return Cadena con informacion del ticket
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String estado = (horaSalida == null) ? "ACTIVO" : "FINALIZADO";
        return "Ticket #" + idTicket + " - " + vehiculo.getPlaca() + " (" + vehiculo.getTipo() + 
               ") - Ingreso: " + horaIngreso.format(formatter) + " - " + estado + 
               (horaSalida != null ? " - Valor: $" + valorPagado : "");
    }
    
}//fin de la clase