
/*
 * Aposta.java
 */

package domini;

import java.io.Serializable;

/**
 * Descripció de la classe concreta Persona.<br>
 * Modela una Aposta en el domini suggerit pels requeriments funcionals
 * 
 * @author Andrés
 */
public class Aposta implements Serializable {

    // Atributs, variables membre o camps: Modelen les dades que la classe i les seves instàncies han de saber o conèixer en tot moment

    private double quantitat;

    // Pseudoatributs per establir i mantenir connexions permanents entre la instància actual i altres instàncies
    
    private Jugador jugador;

    /**
     * 
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors
     * dels paràmetres als atributs<br>
     *
     */
    public Aposta() {
    }

    /**
     *
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors
     * dels paràmetres als atributs<br>
     *
     * @param jugador
     * @param quantitat
     */
    public Aposta(Jugador jugador, double quantitat) {
        this.jugador = jugador;
        this.quantitat = quantitat;
    }

    /**
     * Retorna el valor de l'atribut quantitat.
     * @return la quantitat apostada
     */
    public double getQuantitat() {
        return quantitat;
    }

    /**
     * El nou valor de fitxes apostades
     * @param quantitat
     */
    public void setQuantitat(double quantitat) {
        this.quantitat = quantitat;
    }

    /**
     * Retorna el valor de l'atribut jugador.
     * @return el jugador associat
     */
    public Jugador getJugador() {
        return jugador;
    } 

    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(quantitat);
    }
}
