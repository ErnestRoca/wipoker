/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * prova
 */
package controladora;

import domini.Jugador;
import domini.Ma;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartidaOnline extends ControladoraPartida {

    public ControladoraPartidaOnline(int maxJugadors, ControladoraGui gui) {
        super(maxJugadors, gui);       
    }

    public boolean taulaIsFull() {
        boolean completa = super.taula.getPlaces() - super.taula.getCadiresOcupades() == 0;
        return completa;
    }

    public void afegirJugador(Jugador nouJugador) {
        if (!taulaIsFull()) {
            nouJugador.setMaActual(new Ma());
            super.partida.getJugadors().add(nouJugador);
            super.taula.setCadiresOcupades((super.taula.getCadiresOcupades() + 1));
        }
    }
}

 
