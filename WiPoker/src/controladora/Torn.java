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
    
    private Jugador jugadorTorn;
    private boolean continuar = false;

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

    public void pausa() {
        while (continuar == false) {
            try {
                Thread.sleep(1000);                
            } catch (InterruptedException ex) {
                Logger.getLogger(Torn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void resume() {
        this.continuar = true;
        
    }

    public void run() {
        pausa();
    }

}
