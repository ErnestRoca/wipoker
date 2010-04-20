/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Fase;
import domini.Jugador;
import domini.Partida;
import domini.Ronda;

/**
 *
 * @author ula
 */
public class ControladoraUla {

    //Partida a controlar
    Partida partida;

    public ControladoraUla(Partida partida) {
        this.partida = partida;
    }

    //Afageix jugador
    private void addJugador(Jugador j) {
        partida.getJugadors().add(j);
    }

    //Elimina jugador
    private void removeJugador(Jugador j) {
        partida.getJugadors().remove(j);
    }

    //Afageix ronda
    private void addRonda(Ronda r) {
        partida.getRondes().add(r);
    }

    //Elimina ronda
    private void removeRonda(Ronda r) {
        partida.getRondes().remove(r);
    }

    //Esatabelix el guanyador de la partida
    private void estabelixGuanyadorPartida(Jugador j) {
        partida.setJugadorGuanyador(j);
    }

    //private void estableixDataPartida(Calendar c)   No se si cal posar-ho

    //Afageix fase
    private void addFase(Fase f, byte numRonda) {
        partida.getRondes().get(numRonda).getFases().add(f);
    }

    //Elimina fase
    private void removeFase(Fase f, byte numRonda) {
        partida.getRondes().get(numRonda).getFases().remove(f);
    }

    //Estableix el guanyador de la ronda
    private void estabelixGuanyadorRonda(Jugador j, byte numRonda) {
        partida.getRondes().get(numRonda).setJugadorGuanyador(j);
    }

    //Estableix el pot de la ronda
    private void estabelixPotRonda(double pot, byte numRonda) {
        partida.getRondes().get(numRonda).setPot(pot);
    }


}
