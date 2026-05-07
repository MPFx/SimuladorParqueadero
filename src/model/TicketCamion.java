package model;

/**
 * Clase que representa un ticket para Camion.
 * Hereda de Ticket sin atributos adicionales.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Ticket
 */
public class TicketCamion extends Ticket {
    
    /**
     * Constructor para crear un ticket de camion.
     * 
     * @param vehiculo Vehiculo asociado
     */
    public TicketCamion(Vehiculo vehiculo) {
        super(vehiculo);
    }
    
    /**
     * Devuelve una representacion textual del ticket de camion.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return "[CAMION] " + super.toString();
    }
    
}//fin de la clase