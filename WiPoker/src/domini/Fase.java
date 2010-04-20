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
    private static byte numFase = 0;
    private static final String[] fases = {"pre-flop", "flop", "turn", "river"};

    public Fase(String nomFase) {
        numFase++;
        this.nomFase = nomFase;
    }

    public String getNomFase() {
        return nomFase;
    }

    public static byte getNumFase() {
        return numFase;
    }

    public static String[] getFases() {
        return fases;
    }

    public static void setNumFase(byte numFase) {
        Fase.numFase = numFase;
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
