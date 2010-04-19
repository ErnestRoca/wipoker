package domini;


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
    Jugador jugadorGuanyador;
    //Data de la partida
    Calendar dataPartida;

    //Constructor per defecte
    public Partida() {

    }

    public Partida(Jugador jugadorGuanyador, Calendar dataPartida) {
        this.jugadorGuanyador = jugadorGuanyador;
        this.dataPartida = dataPartida;
    }

    public Calendar getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Calendar dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Jugador getJugadorGuanyador() {
        return jugadorGuanyador;
    }

    public void setJugadorGuanyador(Jugador jugadorGuanyador) {
        this.jugadorGuanyador = jugadorGuanyador;
    }

    @Override
    public String toString() {
        return ("Partida jugada: " + dataPartida.toString() + ", guanyador: " + jugadorGuanyador.nomComplet);
    }
}
