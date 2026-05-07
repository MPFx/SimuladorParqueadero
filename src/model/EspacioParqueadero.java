package model;

/**
 * Clase que representa un espacio de parqueadero.
 * Contiene informacion del piso, numero, tipo de vehiculo permitido y estado.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Parqueadero
 */
public class EspacioParqueadero {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico del espacio. */
    private int idEspacio;
    
    /** Piso donde se encuentra el espacio. */
    private int piso;
    
    /** Numero del espacio dentro del piso. */
    private int numero;
    
    /** Tipo de vehiculo permitido (Carro, Moto, Camion, Bicicleta). */
    private String tipoVehiculo;
    
    /** Indica si el espacio esta ocupado. */
    private boolean ocupado;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear un espacio de parqueadero.
     * 
     * @param piso Piso del espacio
     * @param numero Numero del espacio
     * @param tipoVehiculo Tipo de vehiculo permitido
     */
    public EspacioParqueadero(int piso, int numero, String tipoVehiculo) {
        this.idEspacio = contadorIds++;
        this.piso = piso;
        this.numero = numero;
        this.tipoVehiculo = tipoVehiculo;
        this.ocupado = false;
    }
    
    // ==================== GETTERS ====================
    
    /** @return Identificador del espacio */
    public int getIdEspacio() {
        return idEspacio;
    }
    
    /** @return Piso del espacio */
    public int getPiso() {
        return piso;
    }
    
    /** @return Numero del espacio */
    public int getNumero() {
        return numero;
    }
    
    /** @return Tipo de vehiculo permitido */
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }
    
    /** @return true si esta ocupado */
    public boolean isOcupado() {
        return ocupado;
    }
    
    // ==================== SETTERS ====================
    
    /** @param ocupado Nuevo estado de ocupacion */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    /**
     * Devuelve una representacion textual del espacio.
     * 
     * @return Cadena con piso, numero y tipo
     */
    @Override
    public String toString() {
        return "Piso " + piso + " - Espacio " + numero + " (" + tipoVehiculo + ")" + 
               (ocupado ? " [OCUPADO]" : " [DISPONIBLE]");
    }
    
}//fin de la clase