/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Bot;
import domini.Carta;
import domini.Fase;
import domini.Jugador;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private JButton jbcheck;
    private JButton jbBet;
    private JButton jbFold;
    private JButton jbRise;
    private ControladoraJabber cjabber;

    public ControladoraGui() {
        cjabber = new ControladoraJabber();
    }

    public ControladoraGui(ControladoraPartida cp) {
        login = false;
        faseActual = new Fase();
        this.cp = cp;
        cjabber = new ControladoraJabber();
    }

    public JButton getJbBet() {
        return jbBet;
    }

    public JButton getJbFold() {
        return jbFold;
    }

    public JButton getJbRise() {
        return jbRise;
    }

    public JButton getJbcheck() {
        return jbcheck;
    }

    public GuiTaulell getTaulell() {
        return taulell;
    }

    public ControladoraPartida getCp() {
        return cp;
    }

    public void setCp(ControladoraPartida cp) {
        this.cp = cp;
    }

    public ControladoraJabber getCjabber() {
        return cjabber;
    }

    public void setLogin(boolean login) {
        this.login = login;
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

    public void setTaulell(GuiTaulell t) {
        this.taulell = t;
        jbcheck = taulell.getJbCheck();
        jbBet = taulell.getJbBet();
        jbFold = taulell.getJbFold();
        jbRise = taulell.getJbRise();
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
        cp.controlJoc.ferFold(tornActual.getJugadorTorn(), faseActual);
    }

    //Fa rise
    public void doRise(int dinersAfegits) {
        cp.controlJoc.ferRaise(tornActual.getJugadorTorn(), faseActual, faseActual.getApostaMinima(), dinersAfegits);
        gestionarFitxes(cp.partida.getJugadors());
    }

    //Fa bet
    public void doCall() {
        cp.controlJoc.ferCall(tornActual.getJugadorTorn(), faseActual, faseActual.getApostaMinima());
        gestionarFitxes(cp.partida.getJugadors());
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
    public void mostrarAvatars(ArrayList<Jugador> jugadors) {
        for (int i = 0; i < jugadors.size(); i++) {
            taulell.getAvatars().get(i).setIcon(jugadors.get(i).getAvatar());
            taulell.getPanellsJugadors().get(i).setVisible(true);
        }
    }

    //Posa les imatges dels jugadors a la taula juntament amb els seus diners i nom.
    public void ocultarPanellsJugadors() {
        ArrayList<JPanel> panells = taulell.getPanellsJugadors();
        for (int i = 0; i < panells.size(); i++) {
            panells.get(i).setVisible(false);
        }
    }

    //Posa les imatges dels jugadors a la taula juntament amb els seus diners i nom.
    public void gestionarFitxes(ArrayList<Jugador> jugadors) {
        ArrayList<JLabel> fitxes = taulell.getFitxesjugadors();
        for (int i = 0; i < jugadors.size(); i++) {//a<= x && x<= b
            if (jugadors.get(i).getFitxesActuals() <= ((25 * jugadors.get(0).getFitxesInicials()) / 100)) {
                fitxes.get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 1 + ".png")));

            } else if (((25 * jugadors.get(0).getFitxesInicials()) / 100) <= jugadors.get(i).getFitxesActuals() && jugadors.get(i).getFitxesActuals() <= ((50 * jugadors.get(0).getFitxesInicials()) / 100)) {
                fitxes.get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 2 + ".png")));

            } else if (((50 * jugadors.get(0).getFitxesInicials()) / 100) <= jugadors.get(i).getFitxesActuals() && jugadors.get(i).getFitxesActuals() <= ((75 * jugadors.get(0).getFitxesInicials()) / 100)) {
                fitxes.get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 3 + ".png")));

            } else if (((75 * jugadors.get(0).getFitxesInicials()) / 100) <= jugadors.get(i).getFitxesActuals() && jugadors.get(i).getFitxesActuals() <= ((100 * jugadors.get(0).getFitxesInicials()) / 100)) {
                fitxes.get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
            }
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

    //Posa les cartes comunitaries damunt la taula.
    public void ocultarCartesComunitaries() {
        taulell.getJlCarta01().setVisible(false);
        taulell.getJlCarta02().setVisible(false);
        taulell.getJlCarta03().setVisible(false);
        taulell.getJlCarta04().setVisible(false);
        taulell.getJlCarta05().setVisible(false);
    }

    //Posa les cartes comunitaries damunt la taula.
    public void setAvatarJugadorActiu(Jugador jugador) {
        if (jugador instanceof Bot) {
            taulell.getAvatars().get(jugador.getPosicioTaula()).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/botActiu.png")));
        }

    }

    //Posa les cartes comunitaries damunt la taula.
    public void setAvatarJugadorInActiu(Jugador jugador) {
        if (jugador instanceof Bot) {
            taulell.getAvatars().get(jugador.getPosicioTaula()).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/bot.png")));
        }

    }
}
