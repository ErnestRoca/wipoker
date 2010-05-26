/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Carta;
import domini.Fase;
import domini.Jugador;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacio.GuiTaulell;
import presentacio.partida.GuiLoginJabberPartida;

/**
 *
 * @author ula
 */
public class ControladoraGui {

    private boolean login;
    private ControladoraPartida cp;
    private Torn tornActual = new Torn();
    private Fase faseActual = new Fase();
    /** Pseudoatribut per implementar visibilitat d'atribut. */
    private GuiTaulell taulell;

    public ControladoraGui() {

    }

    public ControladoraGui(ControladoraPartida cp) {
        login = false;
        faseActual = new Fase();
        this.cp = cp;
    }

    public ControladoraPartida getCp() {
        return cp;
    }

    public void setCp(ControladoraPartida cp) {
        this.cp = cp;
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

    public void setTaulell (GuiTaulell t) {
        this.taulell = t;
    }

    public Torn getTornActual() {
        return tornActual;
    }

    public void setTornActual(Torn tornActual) {
        this.tornActual = tornActual;
    }   

    @Deprecated
    public void comprovarLogin(GuiLoginJabberPartida guiNovaPartida) {
        if (!isLogin()) {
            //guiNovaPartida.getJbCrearPartida().setEnabled(false);
            //guiNovaPartida.getJbUnirsePartida().setEnabled(false);
        }
    }

    //Iniciar la partida quan totes les plaçes estan ocupades.
    public void iniciarPartida() throws InterruptedException {
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    cp.jugar();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControladoraGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
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

    //Posa les imatges dels jugadors a la taula juntament amb els seus diners i nom.
    public void mostrarAvatars(ArrayList<Jugador> jugadors){
        for (int i = 0; i < jugadors.size(); i++) {
            taulell.getAvatars().get(i).setIcon(jugadors.get(i).getImatge());
            taulell.getAvatars().get(i).setVisible(true);
        }
    }

    //Posa les cartes comunitaries damunt la taula.
    public void mostrarCartesComunitaries(ArrayList<Carta> publiques) {
        if (Fase.getNumFase() == 1) {
        } else if (Fase.getNumFase() == 2) {
            taulell.getJlCarta01().setIcon(publiques.get(0).getImatge());
            taulell.getJlCarta01().setVisible(true);

            taulell.getJlCarta02().setIcon(publiques.get(1).getImatge());
            taulell.getJlCarta02().setVisible(true);

            taulell.getJlCarta03().setIcon(publiques.get(2).getImatge());
            taulell.getJlCarta03().setVisible(true);
        } else if (Fase.getNumFase() == 3) {
            taulell.getJlCarta04().setIcon(publiques.get(0).getImatge());
            taulell.getJlCarta04().setVisible(true);
        } else if (Fase.getNumFase() == 4) {
            taulell.getJlCarta05().setIcon(publiques.get(0).getImatge());
            taulell.getJlCarta05().setVisible(true);
        }
    }
}
