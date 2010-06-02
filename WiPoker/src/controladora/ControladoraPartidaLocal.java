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
        super(2, gui);
        super.partida.getJugadors().add(new Jugador("324", alias, alias, 0, 0, fitxesInicials, alias, fitxesInicials, 0, "avatar"));
        super.partida.getJugadors().add(new Bot("bot1", fitxesInicials, 1));
//        super.partida.getJugadors().add(new Bot("bot2", fitxesInicials, 3));
//        super.partida.getJugadors().add(new Bot("bot3", fitxesInicials, 4));
//        super.partida.getJugadors().add(new Bot("bot4", fitxesInicials, 5));
//        super.partida.getJugadors().add(new Bot("bot5", fitxesInicials, 6));
//        super.partida.getJugadors().add(new Bot("bot6", fitxesInicials, 7));
//        super.partida.getJugadors().add(new Bot("bot7", fitxesInicials, 8));

    }   
}
