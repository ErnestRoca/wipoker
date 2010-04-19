
import java.util.ArrayList;

/**
 *
 * @author ernest
 */
public class Ma {

    ArrayList<Carta> Cartes = new ArrayList<Carta>(5);

    public Ma(ArrayList<Carta> Cartes) {
        this.Cartes = Cartes;
    }

    public ArrayList<Carta> getCartes() {
        return Cartes;
    }

    public void setCartes(ArrayList<Carta> Cartes) {
        this.Cartes = Cartes;
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
