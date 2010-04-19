package domini;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ula
 */
public class Fase {

    //Nom de la fase (pre-flop, flop, turn. river)
    private final String nomFase;

    public Fase(String nomFase) {
        this.nomFase = nomFase;
    }

    public String getNomFase() {
        return nomFase;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fase other = (Fase) obj;
        if ((this.nomFase == null) ? (other.nomFase != null) : !this.nomFase.equals(other.nomFase)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.nomFase != null ? this.nomFase.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ("Fase: " + nomFase);
    }
}
