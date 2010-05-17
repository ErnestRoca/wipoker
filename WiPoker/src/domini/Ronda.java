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
    private ArrayList<Jugador> jugadorsGuanyadorRonda;
    //pot de la ronda
    private int pot;
    private ArrayList<Fase> fases = new ArrayList<Fase>();
    private int bigBlind;

    public Ronda() {
    }

    public Ronda(double pot) {
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

    public ArrayList<Jugador> getJugadorGuanyadorRonda() {
        return jugadorsGuanyadorRonda;
    }

    public void setJugadorGuanyadorRonda(ArrayList<Jugador> jugadorGuanyadorRonda) {
        this.jugadorsGuanyadorRonda = jugadorGuanyadorRonda;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }



    @Override
    public String toString() {
        return ("Pot : " + pot + ", guanyador: " + jugadorsGuanyadorRonda.toString());
    }
}
