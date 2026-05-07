package model;

/**
 * Clase que representa un ticket para Carro.
 * Hereda de Ticket sin atributos adicionales.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Ticket
 */
public class TicketCarro extends Ticket {
    
    /**
     * Constructor para crear un ticket de carro.
     * 
     * @param vehiculo Vehiculo asociado
     */
    public TicketCarro(Vehiculo vehiculo) {
        super(vehiculo);
    }
    
    /**
     * Devuelve una representacion textual del ticket de carro.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return "[CARRO] " + super.toString();
    }
    
}//fin de la clase