package model;

/**
 * Clase que representa una Bicicleta.
 * Hereda de Vehiculo con tarifa de $200 por hora y ocupa 1 espacio.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Vehiculo
 */
public class Bicicleta extends Vehiculo {
    
    /**
     * Constructor para crear una bicicleta.
     * 
     * @param placa Placa de la bicicleta
     */
    public Bicicleta(String placa) {
        super(placa);
        this.tipo = "Bicicleta";
    }
    
    /**
     * Obtiene la tarifa por hora de la bicicleta.
     * 
     * @return $200 por hora
     */
    @Override
    public double getTarifaPorHora() {
        return 200;
    }
    
    /**
     * Obtiene la cantidad de espacios que ocupa la bicicleta.
     * 
     * @return 1 espacio
     */
    @Override
    public int getEspaciosOcupados() {
        return 1;
    }
    
}//fin de la clase