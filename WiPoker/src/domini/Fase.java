package domini;

import java.util.ArrayList;

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
    private static final String[] nomFases = {"pre-flop", "flop", "turn", "river"};
    private Ronda ronda;
    private ArrayList<Aposta> apostes;

    public Fase(String nomFase) {        
        this.nomFase = nomFase;
        numFase++;
    }

    public String getNomFase() {
        return nomFase;
    }

    public static byte getNumFase() {
        return numFase;
    }

    public static String[] getFases() {
        return nomFases;
    }

    public static void setNumFase(byte numFase) {
        Fase.numFase = numFase;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public ArrayList<Aposta> getApostes() {
        return apostes;
    }

    public void setApostes(ArrayList<Aposta> apostes) {
        this.apostes = apostes;
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
