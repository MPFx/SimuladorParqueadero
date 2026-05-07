package model;

/**
 * Clase que representa un Camion.
 * Hereda de Vehiculo con tarifa de $2000 por hora y ocupa 2 espacios.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Vehiculo
 */
public class Camion extends Vehiculo {
    
    /**
     * Constructor para crear un camion.
     * 
     * @param placa Placa del camion
     */
    public Camion(String placa) {
        super(placa);
        this.tipo = "Camion";
    }
    
    /**
     * Obtiene la tarifa por hora del camion.
     * 
     * @return $2000 por hora
     */
    @Override
    public double getTarifaPorHora() {
        return 2000;
    }
    
    /**
     * Obtiene la cantidad de espacios que ocupa el camion.
     * 
     * @return 2 espacios
     */
    @Override
    public int getEspaciosOcupados() {
        return 2;
    }
    
}//fin de la clase