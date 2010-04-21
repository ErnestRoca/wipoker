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
    private Partida partida;
    //Jugador que guanya la ronda
    private Jugador jugadorGuanyadorRonda;
    //pot de la ronda
    private int pot;
    private ArrayList<Fase> fases = new ArrayList<Fase>();

    public Ronda() {
    }

    public Ronda(double pot) {        
    }

    public Jugador getJugadorGuanyador() {
        return jugadorGuanyadorRonda;
    }

    public void setJugadorGuanyador(Jugador jugadorGuanyador) {
        this.jugadorGuanyadorRonda = jugadorGuanyador;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public ArrayList<Fase> getFases() {
        return fases;
    }

    public void setFases(ArrayList<Fase> fases) {
        this.fases = fases;
    }

    public Jugador getJugadorGuanyadorRonda() {
        return jugadorGuanyadorRonda;
    }

    public void setJugadorGuanyadorRonda(Jugador jugadorGuanyadorRonda) {
        this.jugadorGuanyadorRonda = jugadorGuanyadorRonda;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    @Override
    public String toString() {
        return ("Pot : " + pot  + ", guanyador: " + jugadorGuanyadorRonda.getNomComplet());
    }
}
