/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora;

import domini.Jugador;
import java.awt.event.ActionListener;

/**
 *
 * @author wida45787385
 */
public class Torn implements Runnable {

    private boolean running;
    private Jugador jugadorTorn;
    public Torn(Jugador jugador) {
        this.jugadorTorn = jugador;
    }

    public void run() {
        
    }

    public void pause() {
        
    }

    public void resume(String accio) {

    }

    public void stop() {
        
    }

}
