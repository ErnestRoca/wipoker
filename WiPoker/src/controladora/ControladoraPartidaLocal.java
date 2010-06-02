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
    
    public ControladoraPartidaLocal(ControladoraGui gui, String alias, int fitxesInicials) {
        super(8, gui);
        super.partida.getJugadors().add(new Jugador("324", alias, alias, 0, 0, fitxesInicials, alias, fitxesInicials, 0, "avatar"));
        super.partida.getJugadors().add(new Bot("bot1", fitxesInicials));
        super.partida.getJugadors().add(new Bot("bot2", fitxesInicials));
        super.partida.getJugadors().add(new Bot("bot3", fitxesInicials));
        super.partida.getJugadors().add(new Bot("bot4", fitxesInicials));
        super.partida.getJugadors().add(new Bot("bot5", fitxesInicials));
        super.partida.getJugadors().add(new Bot("bot6", fitxesInicials));
        super.partida.getJugadors().add(new Bot("bot7", fitxesInicials));
        //super.partida.getJugadors().add(new Bot("bot8", fitxesInicials));
    }   
}
