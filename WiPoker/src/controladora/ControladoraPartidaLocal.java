/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Bot;
import domini.Jugador;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartidaLocal extends ControladoraPartida {
    
    public ControladoraPartidaLocal(ControladoraGui gui, String alias) {
        super(2, gui);
        super.partida.getJugadors().add(new Jugador(alias));
        super.partida.getJugadors().add(new Bot("bot", 1000));
    }

   
}
