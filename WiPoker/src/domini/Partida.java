package domini;


import java.util.ArrayList;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ula
 */
public class Partida {
    //Jugador que guanya la partida
    private Jugador jugadorGuanyador;
    //Data de la partida
    private final Calendar dataPartida;
    //Arraylist de jugadors
    private ArrayList<Jugador> jugadors = new ArrayList<Jugador>();

    //ArrayList de rondes
    private ArrayList<Ronda> rondes = new ArrayList<Ronda>();

    public Partida(Calendar dataPartida) {        
        this.dataPartida = dataPartida;
    }

    public Calendar getDataPartida() {
        return dataPartida;
    }   

    public Jugador getJugadorGuanyador() {
        return jugadorGuanyador;
    }

    public void setJugadorGuanyador(Jugador jugadorGuanyador) {
        this.jugadorGuanyador = jugadorGuanyador;
    }

    public ArrayList<Jugador> getJugadors() {
        return jugadors;
    }

    public void setJugadors(ArrayList<Jugador> jugadors) {
        this.jugadors = jugadors;
    }

    public ArrayList<Ronda> getRondes() {
        return rondes;
    }

    public void setRondes(ArrayList<Ronda> rondes) {
        this.rondes = rondes;
    }
    
    @Override
    public String toString() {
        return ("Partida jugada: " + dataPartida.toString() + ", guanyador: " + jugadorGuanyador.getNomComplet());
    }
}
