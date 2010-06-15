/**
 * Baralla.java
 */
package domini;

import java.util.ArrayList;

/**
 * Descripció de la classe concreta Baralla.<br>
 * Modela una Baralla en el domini suggerit pels requeriments funcionals
 * 
 * @author ernest
 */
public class Baralla {

    // Atributs, variables membre o camps: Modelen les dades que la classe i les seves instàncies han de saber o conèixer en tot moment
    
    private static final byte maxCartes = 52;       //Nombre total de cartes de que estarà formada la Baralla.
    private byte cartesActuals;                     //Nombre de cartes que queden per descartar.

    // Pseudoatributs per establir i mantenir connexions permanents entre la instància actual i altres instàncies

    private ArrayList<Carta> Cartes = new ArrayList<Carta>();

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors
     * dels paràmetres als atributs<br>
     * 
     */
    public Baralla() {
        this.cartesActuals = maxCartes;
    }

    /**
     * Retorna el valor de l'atribut Cartes.
     * @return les cartes que formen la baralla
     */
    public ArrayList<Carta> getCartes() {
        return Cartes;
    }

    /**
     * El nou valor de Cartes
     * @param Cartes
     */
    public void setCartes(ArrayList<Carta> Cartes) {
        this.Cartes = Cartes;
    }

    /**
     * Retorna el valor de l'atribut Cartes.
     * @return Nombre de cartes que queden per descartar
     */
    public byte getCartesActuals() {
        return cartesActuals;
    }

    /**
     * El nou valor de cartesActuals
     * @param Nombre de cartes que queden per descartar
     */
    public void setCartesActuals(int cartesActuals) {
        this.cartesActuals = (byte) cartesActuals;
    }

    /**
     * Compara la instància actual amb una referència a una altra instància per determinar-ne la seva "igualtat".<br>
     * pre: Les indicades als paràmetres<br>
     * post: Les indicades al return<br>
     * @param obj Una referència a una instància Object
     * @return true si considerem ambdós instàncies "iguals" (mateix objecte a nivell del domini)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Baralla other = (Baralla) obj;
        if (this.Cartes != other.Cartes && (this.Cartes == null || !this.Cartes.equals(other.Cartes))) {
            return false;
        }
        if (this.cartesActuals != other.cartesActuals) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.Cartes != null ? this.Cartes.hashCode() : 0);
        hash = 41 * hash + this.cartesActuals;
        return hash;
    }

    @Override
    public String toString() {
        String cad = "Baralla de " + maxCartes + " cartes, formada per : \n";
        for (int i = 0; i < Cartes.size(); i++) {
            cad += Cartes.get(i);
        }
        return cad;
    }
}
