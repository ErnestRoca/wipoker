/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora;

import domini.Jugador;

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

    public void run() {
        
    }

    public void pause() {
        
    }

    public void resume(String accio) {

    }

    public void stop() {
        
    }

}
