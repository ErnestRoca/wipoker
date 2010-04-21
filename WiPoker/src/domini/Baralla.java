package domini;


import java.util.ArrayList;

/**
 *
 * @author ernest
 */
public class Baralla {

    private ArrayList<Carta> Cartes = new ArrayList<Carta>();
    private static final byte maxCartes = 52;         //Nombre total de cartes de que estarà formada la Baralla.
    private byte cartesActuals;     //Nombre de cartes que queden per descartar.

    public Baralla() {
        this.cartesActuals = maxCartes;
    }

    public ArrayList<Carta> getCartes() {
        return Cartes;
    }

    public void setCartes(ArrayList<Carta> Cartes) {
        this.Cartes = Cartes;
    }

    public byte getCartesActuals() {
        return cartesActuals;
    }

    public void setCartesActuals(byte cartesActuals) {
        this.cartesActuals = cartesActuals;
    }

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
