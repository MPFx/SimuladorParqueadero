package model;

/**
 * Clase que representa un Carro.
 * Hereda de Vehiculo con tarifa de $1000 por hora y ocupa 1 espacio.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Vehiculo
 */
public class Carro extends Vehiculo {
    
    /**
     * Constructor para crear un carro.
     * 
     * @param placa Placa del carro
     */
    public Carro(String placa) {
        super(placa);
        this.tipo = "Carro";
    }
    
    /**
     * Obtiene la tarifa por hora del carro.
     * 
     * @return $1000 por hora
     */
    @Override
    public double getTarifaPorHora() {
        return 1000;
    }
    
    /**
     * Obtiene la cantidad de espacios que ocupa el carro.
     * 
     * @return 1 espacio
     */
    @Override
    public int getEspaciosOcupados() {
        return 1;
    }
    
}//fin de la clase