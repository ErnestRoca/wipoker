
/**
 * Ma.java
 */

// Per assignar la classe actual a la capa domini
package domini;

// Per implementar associacions i navegabilitats
import java.util.ArrayList;

/**
 * Descripció de la classe concreta Carta.<br>
 * Modela un Bot(jugador automàtic) en el domini suggerit pels requeriments funcionals
 * 
 * @author ernest
 */
public class Ma {

    // Atributs, variables membre o camps: Modelen les dades que la classe i les seves instàncies han de saber o conèixer en tot moment
    
    private byte combinacio;
    private byte valorMesAlt = 0;
    private byte valorDesempat = 0;

    // Pseudoatributs per establir i mantenir connexions permanents entre la instància actual i altres instàncies
    
    private ArrayList<Carta> Cartes = new ArrayList<Carta>();
    private Fase fase;

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors
     * dels paràmetres als atributs<br>
     * 
     */
    public Ma() {
    }
    /**
     * 
     * @param Cartes
     */
    public Ma(ArrayList<Carta> Cartes) {
        this.Cartes = Cartes;
    }

    public ArrayList<Carta> getCartes() {
        return Cartes;
    }

    public void setCartes(ArrayList<Carta> Cartes) {
        this.Cartes = Cartes;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public byte getCombinacio() {
        return combinacio;
    }

    public void setCombinacio(int combinacio) {
        this.combinacio = (byte) combinacio;
    }

    public byte getValorMesAlt() {
        return valorMesAlt;
    }

    public void setValorMesAlt(int valorMesAlt) {
        this.valorMesAlt = (byte) valorMesAlt;
    }

    public byte getValorDesempat() {
        return valorDesempat;
    }

    public void setValorDesempat(int valorDesempat) {
        this.valorDesempat = (byte) valorDesempat;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ma other = (Ma) obj;
        if (this.Cartes != other.Cartes && (this.Cartes == null || !this.Cartes.equals(other.Cartes))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.Cartes != null ? this.Cartes.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        String cad = "Mà formada per : \n";
        for (int i = 0; i < Cartes.size(); i++) {
            cad += Cartes.get(i);
        }
        return cad;
    }
}
