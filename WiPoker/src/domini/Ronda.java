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
public class Ronda {
    //Jugador que guanya la ronda
    private Jugador jugadorGuanyador;
    //pot de la ronda
    private double pot;
    private ArrayList<Fase> fases = new ArrayList<Fase>(5);

    public Ronda() {
    }

    public Ronda(double pot) {        
    }

    public Jugador getJugadorGuanyador() {
        return jugadorGuanyador;
    }

    public void setJugadorGuanyador(Jugador jugadorGuanyador) {
        this.jugadorGuanyador = jugadorGuanyador;
    }

    public double getPot() {
        return pot;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    public ArrayList<Fase> getFases() {
        return fases;
    }

    public void setFases(ArrayList<Fase> fases) {
        this.fases = fases;
    }



    @Override
    public String toString() {
        return ("Pot : " + pot  + ", guanyador: " + jugadorGuanyador.getNomComplet());
    }
}
