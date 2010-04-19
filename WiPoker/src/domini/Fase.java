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
    String nomFase;

    //constructor per defecte
    public Fase() {
    }

    public Fase(String nomFase) {
        this.nomFase = nomFase;
    }

    public String getNomFase() {
        return nomFase;
    }

    public void setNomFase(String nomFase) {
        this.nomFase = nomFase;
    }

    @Override
    public String toString() {
        return ("Fase: " + nomFase);
    }
}
