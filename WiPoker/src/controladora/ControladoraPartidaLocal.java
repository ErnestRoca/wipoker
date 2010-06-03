/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Bot;
import domini.Jugador;
import java.util.Arrays;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartidaLocal extends ControladoraPartida {
    String[] nomBots = {"Joan", "Marc", "Oriol", "Ramona", "Abelardo", "Messi",
    "Josep", "Bush", "Kennedy", "MoneyMaker", "Negreanu", "Reymer", "Minieri",
    "Humberto", "Greenstein", "Pagano", "Victoria", "Hansen"};
    
    public ControladoraPartidaLocal(ControladoraGui gui, String alias, int fitxesInicials, int numJugadors) {
        super(numJugadors, gui);
        Arrays.sort(nomBots);
        super.partida.getJugadors().add(new Jugador(alias, fitxesInicials, 0, "avatar"));
        for (int i = 1; i < numJugadors;i++) {
            super.partida.getJugadors().add(new Bot(nomBots[i], fitxesInicials, i));
        }
    }   
}
