/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora;

import domini.Jugador;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wida45787385
 */
public class Torn implements Runnable {

    private boolean running;
    private boolean stop;
    private Jugador jugadorTorn;

    public Torn() {
        
    }
    
    public Torn(Jugador jugador) {
        this.jugadorTorn = jugador;
    }

    public Jugador getJugadorTorn() {
        return jugadorTorn;
    }

    public void setJugadorTorn(Jugador jugadorTorn) {
        this.jugadorTorn = jugadorTorn;
    }

    public synchronized void run() {
        resume();
    }

    public void pause() {
        while (running = false) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Torn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void resume() {
        running = true;
    }
}
