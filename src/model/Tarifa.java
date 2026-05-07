package model;

/**
 * Interfaz que define el comportamiento para calcular tarifas de parqueadero.
 * Las clases que implementen esta interfaz podran calcular la tarifa
 * segun las horas de estadia y el tipo de vehiculo.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Ticket
 */
public interface Tarifa {
    
    /**
     * Calcula la tarifa a pagar segun las horas de estadia.
     * 
     * @param horas Numero de horas de estadia
     * @return Monto a pagar
     */
    double calcularTarifa(double horas);
    
}//fin de la interfaz