/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * prova
 */
package controladora;

import domini.Jugador;
import domini.Ma;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartidaOnline extends ControladoraPartida {

    public ControladoraJabber cj;

    public ControladoraPartidaOnline(int maxJugadors, ControladoraGui gui) {
        super(maxJugadors, gui);
        try {
            cj = new ControladoraJabber("jabberes.org");
        } catch (XMPPException ex) {
            Logger.getLogger(ControladoraPartidaOnline.class.getName()).log(Level.SEVERE, null, ex);
        }
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

 
