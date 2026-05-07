package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase abstracta que representa un vehiculo en el parqueadero.
 * Contiene los atributos y comportamientos comunes a todos los vehiculos
 * (Carro, Moto, Camion, Bicicleta).
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Carro
 * @see Moto
 * @see Camion
 * @see Bicicleta
 */
public abstract class Vehiculo {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico del vehiculo. */
    protected int idVehiculo;
    
    /** Placa del vehiculo. */
    protected String placa;
    
    /** Tipo de vehiculo. */
    protected String tipo;
    
    /** Hora de ingreso al parqueadero. */
    protected LocalDateTime horaIngreso;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear un vehiculo.
     * 
     * @param placa Placa del vehiculo
     */
    public Vehiculo(String placa) {
        this.idVehiculo = contadorIds++;
        this.placa = placa;
        this.horaIngreso = LocalDateTime.now();
    }
    
    /**
     * Obtiene la tarifa por hora segun el tipo de vehiculo.
     * Metodo abstracto implementado por las subclases.
     * 
     * @return Tarifa por hora
     */
    public abstract double getTarifaPorHora();
    
    /**
     * Obtiene la cantidad de espacios que ocupa el vehiculo.
     * Metodo abstracto implementado por las subclases.
     * 
     * @return Numero de espacios ocupados
     */
    public abstract int getEspaciosOcupados();
    
    // ==================== GETTERS Y SETTERS ====================
    
    /** @return Identificador del vehiculo */
    public int getIdVehiculo() {
        return idVehiculo;
    }
    
    /** @return Placa del vehiculo */
    public String getPlaca() {
        return placa;
    }
    
    /** @param placa Nueva placa */
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    /** @return Tipo de vehiculo */
    public String getTipo() {
        return tipo;
    }
    
    /** @param tipo Nuevo tipo */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /** @return Hora de ingreso */
    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }
    
    /** @param horaIngreso Nueva hora de ingreso */
    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }
    
    /**
     * Devuelve una representacion textual del vehiculo.
     * 
     * @return Cadena con placa y tipo
     */
    @Override
    public String toString() {
        return placa + " (" + tipo + ")";
    }
    
}//fin de la clase