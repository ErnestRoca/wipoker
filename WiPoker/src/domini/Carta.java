
/*
 * Carta.java
 */

// Per assignar la classe actual a la capa domini
package domini;

// Per implementar associacions i navegabilitats
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Descripció de la classe concreta Carta.<br>
 * Modela un Bot(jugador automàtic) en el domini suggerit pels requeriments funcionals
 * 
 * @author ernest
 */
public class Carta implements Comparable, Serializable {

    // Atributs, variables membre o camps: Modelen les dades que la classe i les seves instàncies han de saber o conèixer en tot moment
    
    private final byte pal;
    private final byte valor;
    private ImageIcon imatge;

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors
     * dels paràmetres als atributs<br>
     * 
     * @param pal
     * @param valor
     */
    public Carta(int pal, int valor) {
        this.pal = (byte) pal;
        this.valor = (byte) valor;

        String palCarta = null;
        if (pal == 0) {
            palCarta = "s";
        } else if (pal == 1) {
            palCarta = "d";
        } else if (pal == 2) {
            palCarta = "c";
        } else if (pal == 3) {
            palCarta = "h";
        }
        try {
            imatge = new ImageIcon(Carta.class.getResource("/serveis/imatges/cards/" + valor + palCarta + ".gif"));
        } catch (Exception ex) {
            System.out.println("No s'ha trobat la imatge" + " associada amb el valor: " + valor + " i pal: " + pal);
        }
    }

    /**
     * Retorna el valor de l'atribut pal.
     * @return el pat de la carta
     */
    public byte getPal() {
        return pal;
    }

    /**
     * Retorna el valor de l'atribut valor.
     * @return el valor de la carta
     */
    public byte getValor() {
        return valor;
    }

    /**
     * Retorna el valor de l'atribut imatge.
     * @return imatge associada a la pesa
     */
    public ImageIcon getImatge() {
        return imatge;
    }

    /**
     * Compara la instància actual amb una referència a una altra instància per determinar-ne la seva "igualtat".<br>
     * pre: Les indicades als paràmetres<br>
     * post: Les indicades al return<br>
     * @param obj Una referència a una instància Object
     * @return true si considerem ambdós instàncies "iguals" (mateix objecte a nivell del domini).
     * Una Carta serà diferent quan el seu pal i el seu valor siguin diferents.
     */

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.pal != other.pal) {
            return false;
        }
        if (this.valor != other.valor) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.pal;
        hash = 17 * hash + this.valor;
        return hash;
    }

    @Override
    public String toString() {
        return "Carta amb valor " + valor + " i pal " + pal + "\n";
    }

    @Override
    public int compareTo(Object o) {
        Carta c = (Carta) o;
        return this.valor - c.getValor();
    }
}
