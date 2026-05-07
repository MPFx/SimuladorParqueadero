package model;

/**
 * Clase que representa una Moto.
 * Hereda de Vehiculo con tarifa de $500 por hora y ocupa 1 espacio.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Vehiculo
 */
public class Moto extends Vehiculo {
    
    /**
     * Constructor para crear una moto.
     * 
     * @param placa Placa de la moto
     */
    public Moto(String placa) {
        super(placa);
        this.tipo = "Moto";
    }
    
    /**
     * Obtiene la tarifa por hora de la moto.
     * 
     * @return $500 por hora
     */
    @Override
    public double getTarifaPorHora() {
        return 500;
    }
    
    /**
     * Obtiene la cantidad de espacios que ocupa la moto.
     * 
     * @return 1 espacio
     */
    @Override
    public int getEspaciosOcupados() {
        return 1;
    }
    
}//fin de la clase