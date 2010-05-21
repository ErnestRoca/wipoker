/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import presentacio.partida.GuiNovaPartida;

/**
 *
 * @author ula
 */
public class ControladoraGui {

    private boolean login;
    private ControladoraPartida cp = new ControladoraPartida(1);

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

    public void comprovarLogin(GuiNovaPartida guiNovaPartida) {
        if (!isLogin()) {
            guiNovaPartida.getJbCrearPartida().setEnabled(false);
            guiNovaPartida.getJbUnirsePartida().setEnabled(false);
        }
    }

    //Iniciar la partida quan totes les plaçes estan ocupades.
    public void iniciarPartida() {
        //
    }

    //Fa Check
    public void doCheck() {
        //cp.controlJoc.ferCheck(null, null, apostaMinima);    //
    }

    //Fa Fold
    public void doFold() {
        //
    }

    //Fa rise
    public void doRise() {
        //
    }

    //Fa bet
    public void doBet() {
        //
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
    public boolean busacrJugador() {
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
