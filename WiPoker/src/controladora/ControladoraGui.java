/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Fase;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacio.partida.GuiLoginJabberPartida;

/**
 *
 * @author ula
 */
public class ControladoraGui {

    private boolean login;
    private Object cp;
    private Torn tornActual = new Torn();
    private Fase faseActual;

    public ControladoraGui(Object clase) {
        login = false;
        faseActual = new Fase();
        if (clase instanceof ControladoraPartidaOnline) {
            cp = new ControladoraPartidaOnline(1, this);
        } else if (clase instanceof ControladoraPartidaLocal) {
            cp = new ControladoraPartidaLocal(this);
        }
    }

    public void setLoginTrue() {
        login = true;
    }

    public void setLoginFalse() {
        login = false;
    }

    public boolean isLogin() {
        return login;
    }

    public Fase getFaseActual() {
        return faseActual;
    }

    public void setFaseActual(Fase faseActual) {
        this.faseActual = faseActual;
    }

    public Torn getTornActual() {
        return tornActual;
    }

    public void setTornActual(Torn tornActual) {
        this.tornActual = tornActual;
    }

    public Object getCp() {
        return cp;
    }

    public void comprovarLogin(GuiLoginJabberPartida guiNovaPartida) {
        if (!isLogin()) {
            //guiNovaPartida.getJbCrearPartida().setEnabled(false);
            //guiNovaPartida.getJbUnirsePartida().setEnabled(false);
        }
    }

    //Iniciar la partida quan totes les plaçes estan ocupades.
    public void iniciarPartida() {
        try {
            cp.jugar();
            //
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladoraGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
    }

    //Fa Check
    public void doCheck(int quantitat) {        
        cp.controlJoc.ferCheck(tornActual.getJugadorTorn(), faseActual, quantitat);
    }

    //Fa Fold
    public void doFold() {
        cp.controlJoc.ferFold(tornActual.getJugadorTorn());
    }

    //Fa rise
    public void doRise(int dinersAfegits) {
        cp.controlJoc.ferRaise(tornActual.getJugadorTorn(), faseActual, faseActual.getApostaMinima(), dinersAfegits);
    }

    //Fa bet
    public void doBet(int diners) {
        cp.controlJoc.ferBet(tornActual.getJugadorTorn(), faseActual, diners);
    }

    //Registra un jugador a la base de dades (No Jabber).
    public boolean registrarJugador() {
        return true;
    }

    //Elimina un jugador de la base de dades (No Jabber).
    public boolean eliminarJugador() {
        return true;
    }

    //Busca un jugador de la base de dades (No Jabber).
    public boolean buscarJugador() {
        return true;
    }

    //Inicia la sessio Jabber
    public void iniciarSessio() {
        //
    }

    //Tenca la sessio Jabber
    public void tencarSessio() {
        //
    }

    //Crea una conta Jabber
    public void crearContaJabber() {
        //
    }
}
