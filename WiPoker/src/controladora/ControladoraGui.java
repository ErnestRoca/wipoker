/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Fase;
import presentacio.partida.GuiLoginJabberPartida;

/**
 *
 * @author ula
 */
public class ControladoraGui {

    private boolean login;
    private ControladoraPartida cp = new ControladoraPartida(1);
    private Torn tornActual;
    private Fase faseActual;

    public ControladoraGui() {
        login = false;
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

    public void comprovarLogin(GuiLoginJabberPartida guiNovaPartida) {
        if (!isLogin()) {
            //guiNovaPartida.getJbCrearPartida().setEnabled(false);
            //guiNovaPartida.getJbUnirsePartida().setEnabled(false);
        }
    }

    //Iniciar la partida quan totes les plaçes estan ocupades.
    public void iniciarPartida() {
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
