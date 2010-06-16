/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Bot;
import domini.Jugador;
import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<String> noms = new ArrayList<String>();
        for (String s: nomBots) {
            noms.add(s);
        }
        Collections.shuffle(noms);
        if (gui.getJugadorLocal() == null) {
            super.partida.getJugadors().add(new Jugador(alias, fitxesInicials, 0, "avatar"));
        } else {
            super.partida.getJugadors().add(gui.getJugadorLocal());
        }
        for (int i = 1; i < numJugadors; i++) {
            super.partida.getJugadors().add(new Bot(noms.get(i), fitxesInicials, i));
        }
    }
}
