/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * prova
 */
package controladora;

import domini.Jugador;
import domini.Ma;
import sockets.Servidor;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartidaOnline extends ControladoraPartida {


    public ControladoraPartidaOnline(ControladoraGui gui) {
        super(gui);
    }

    public ControladoraPartidaOnline(int maxJugadors, ControladoraGui gui) {
        super(maxJugadors, gui);  
        
    }

    public void afegirJugador(Jugador nouJugador) {
        if (!taulaIsFull()) {
            nouJugador.setMaActual(new Ma());
            super.partida.getJugadors().add(nouJugador);
            super.taula.setCadiresOcupades((super.taula.getCadiresOcupades() + 1));
        }
    }


}

 
