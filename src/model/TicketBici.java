package model;

/**
 * Clase que representa un ticket para Bicicleta.
 * Hereda de Ticket sin atributos adicionales.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Ticket
 */
public class TicketBici extends Ticket {
    
    /**
     * Constructor para crear un ticket de bicicleta.
     * 
     * @param vehiculo Vehiculo asociado
     */
    public TicketBici(Vehiculo vehiculo) {
        super(vehiculo);
    }
    
    /**
     * Devuelve una representacion textual del ticket de bicicleta.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return "[BICI] " + super.toString();
    }
    
}//fin de la clase