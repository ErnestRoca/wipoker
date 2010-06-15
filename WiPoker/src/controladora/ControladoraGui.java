/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Bot;
import domini.Carta;
import domini.Fase;
import domini.Jugador;
import domini.Partida;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import presentacio.GuiTaulell;
import presentacio.partida.GuiLoginJabberPartida;

/**
 *
 * @author ula
 */
public class ControladoraGui {

    private boolean login;
    private String logs = "";
    private Torn tornActual = new Torn();
    private Fase faseActual = new Fase();
    /** Pseudoatribut per implementar visibilitat d'atribut. */
    private ControladoraPartida cp;
    private ControladoraPartidaOnline cpo;
    private Jugador jugadorLocal;
    private GuiTaulell taulell;
    private ControladoraJabber cjabber;

    public ControladoraGui() {
        cjabber = new ControladoraJabber();
    }

    public ControladoraGui(ControladoraPartida cp) {
        login = false;
        faseActual = new Fase();
        this.cp = cp;
        if (cp instanceof ControladoraPartidaOnline) {
            cpo = (ControladoraPartidaOnline) cp;
        }
        cjabber = new ControladoraJabber();
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
    public void doCheck() {
        cp.controlJoc.ferCheck(tornActual.getJugadorTorn(), faseActual, faseActual.getApostaMinima());
    }

    //Fa Fold
    public void doFold() {
        cp.controlJoc.ferFold(tornActual.getJugadorTorn(), faseActual);
    }

    //Fa rise
    public void doRise() {
        cp.controlJoc.ferRaise(tornActual.getJugadorTorn(), faseActual, faseActual.getApostaMinima(), taulell.getjSldrEntradaFitxes().getValue());
        gestionarFitxes();
    }

    //Fa bet
    public void doCall() {
        cp.controlJoc.ferCall(tornActual.getJugadorTorn(), faseActual, faseActual.getApostaMinima());
        gestionarFitxes();
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
            if (!cp.partida.getJugadors().get(i).isEliminat()) {
                taulell.getAvatars().get(i).setIcon(jugadors.get(i).getAvatar());
                taulell.getPanellsJugadors().get(i).setVisible(true);
                taulell.getNomsJugadors().get(i).setText(jugadors.get(i).getAlias());
            }
        }

    }

    public void ocultarPanellsJugadors() {
        ArrayList<JPanel> panells = taulell.getPanellsJugadors();
        for (int i = 0; i < panells.size(); i++) {
            panells.get(i).setVisible(false);
        }
        taulell.getjPanelMissatges().setVisible(false);
    }

    //Posa les imatges de les fitxes dels jugadors i el seu valor al jlabel
    public void gestionarFitxes() {
        ArrayList<Jugador> jugadors = cp.partida.getJugadors();
        ArrayList<JLabel> fitxes = taulell.getFitxesJugadors();
        for (int i = 0; i < jugadors.size(); i++) {//a<= x && x<= b
            //taulell.getNumFitxesJugadors().get(i).setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
            if (jugadors.get(i).getPosicioTaula() == 0) {
                taulell.getJlTotalFitxes01().setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
                taulell.getJlNumFitxes01().setText("aposta: " + jugadors.get(i).getApostaTotalRonda());
            } else if (jugadors.get(i).getPosicioTaula() == 1) {
                taulell.getJlTotalFitxes02().setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
                taulell.getJlNumFitxes02().setText("aposta: " + jugadors.get(i).getApostaTotalRonda());
            } else if (jugadors.get(i).getPosicioTaula() == 2) {
                taulell.getJlTotalFitxes03().setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
                taulell.getJlNumFitxes03().setText("aposta: " + jugadors.get(i).getApostaTotalRonda());
            } else if (jugadors.get(i).getPosicioTaula() == 3) {
                taulell.getJlTotalFitxes04().setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
                taulell.getJlNumFitxes04().setText("aposta: " + jugadors.get(i).getApostaTotalRonda());
            } else if (jugadors.get(i).getPosicioTaula() == 4) {
                taulell.getJlTotalFitxes05().setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
                taulell.getJlNumFitxes05().setText("aposta: " + jugadors.get(i).getApostaTotalRonda());
            } else if (jugadors.get(i).getPosicioTaula() == 5) {
                taulell.getJlTotalFitxes06().setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
                taulell.getJlNumFitxes06().setText("aposta: " + jugadors.get(i).getApostaTotalRonda());
            } else if (jugadors.get(i).getPosicioTaula() == 6) {
                taulell.getJlTotalFitxes07().setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
                taulell.getJlNumFitxes07().setText("aposta: " + jugadors.get(i).getApostaTotalRonda());
            } else if (jugadors.get(i).getPosicioTaula() == 7) {
                taulell.getJlTotalFitxes08().setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
                taulell.getJlNumFitxes08().setText("aposta: " + jugadors.get(i).getApostaTotalRonda());
            } else if (jugadors.get(i).getPosicioTaula() == 8) {
                taulell.getJlTotalFitxes09().setText(Integer.toString(jugadors.get(i).getFitxesActuals()) + " f.");
                taulell.getJlNumFitxes09().setText("aposta: " + jugadors.get(i).getApostaTotalRonda());
            }

            if (jugadors.get(i).getFitxesActuals() <= ((25 * jugadors.get(0).getFitxesInicials()) / 100)) {
                fitxes.get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 1 + ".png")));

            } else if (((25 * jugadors.get(0).getFitxesInicials()) / 100) <= jugadors.get(i).getFitxesActuals() && jugadors.get(i).getFitxesActuals() <= ((50 * jugadors.get(0).getFitxesInicials()) / 100)) {
                fitxes.get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 2 + ".png")));

            } else if (((50 * jugadors.get(0).getFitxesInicials()) / 100) <= jugadors.get(i).getFitxesActuals() && jugadors.get(i).getFitxesActuals() <= ((75 * jugadors.get(0).getFitxesInicials()) / 100)) {
                fitxes.get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 3 + ".png")));

            } else if (((75 * jugadors.get(0).getFitxesInicials()) / 100) <= jugadors.get(i).getFitxesActuals()) {
                fitxes.get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
            }
        }
    }

    public void gestionarPot() {
        taulell.getJlPotTotal2().setText("" + faseActual.getRonda().getPot());
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

    public void setAvatarJugadorActiu(Jugador jugador) {
        if (jugador instanceof Bot && !jugador.isHaFetFold()) {
            taulell.getAvatars().get(jugador.getPosicioTaula()).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/botActiu.png")));
        }

    }

    public void setAvatarJugadorInActiu(Jugador jugador) {
        if (jugador instanceof Bot && !jugador.isHaFetFold()) {
            taulell.getAvatars().get(jugador.getPosicioTaula()).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/bot.png")));
        }

    }

    public void setCartesPrivades() {
        for (Jugador jugador : cp.partida.getJugadors()) {
            if (!(jugador instanceof Bot) && !jugador.isEliminat()) {
                taulell.getJlCarta01Usuari().setIcon(jugador.getMaActual().getCartes().get(0).getImatge());
                taulell.getJlCarta02Usuari().setIcon(jugador.getMaActual().getCartes().get(1).getImatge());
            }
        }
    }

    public void mostrarMissatge(String missatge, String missatge2) {
        try {
            taulell.getjPanelMissatges().setVisible(true);
            taulell.getJtaMissatge().setText(missatge);
            taulell.getJlMissatge2().setText(missatge2);
            Thread.sleep(5000);
            taulell.getjPanelMissatges().setVisible(false);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladoraGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gestionarJugadorEliminat(Jugador jugador) {
        taulell.getJLButtons().get(jugador.getPosicioTaula()).setVisible(false);
        if (jugador.getPosicioTaula() == 0) {
            taulell.getJlFitxes01().setVisible(false);
            taulell.getJlAvatar01().setVisible(false);
            taulell.getJlNom01().setVisible(false);
            taulell.getJlNumFitxes01().setVisible(false);
            taulell.getJlTotalFitxes01().setVisible(false);
        } else if (jugador.getPosicioTaula() == 1) {
            taulell.getJlFitxes02().setVisible(false);
            taulell.getJlAvatar02().setVisible(false);
            taulell.getJlNom02().setVisible(false);
            taulell.getJlNumFitxes02().setVisible(false);
            taulell.getJlTotalFitxes02().setVisible(false);
        } else if (jugador.getPosicioTaula() == 2) {
            taulell.getJlFitxes03().setVisible(false);
            taulell.getJlAvatar03().setVisible(false);
            taulell.getJlNom03().setVisible(false);
            taulell.getJlNumFitxes03().setVisible(false);
            taulell.getJlTotalFitxes03().setVisible(false);
        } else if (jugador.getPosicioTaula() == 3) {
            taulell.getJlFitxes04().setVisible(false);
            taulell.getJlAvatar04().setVisible(false);
            taulell.getJlNom04().setVisible(false);
            taulell.getJlNumFitxes04().setVisible(false);
            taulell.getJlTotalFitxes04().setVisible(false);
        } else if (jugador.getPosicioTaula() == 4) {
            taulell.getJlFitxes05().setVisible(false);
            taulell.getJlAvatar05().setVisible(false);
            taulell.getJlNom05().setVisible(false);
            taulell.getJlNumFitxes05().setVisible(false);
            taulell.getJlTotalFitxes05().setVisible(false);
        } else if (jugador.getPosicioTaula() == 5) {
            taulell.getJlFitxes06().setVisible(false);
            taulell.getJlAvatar06().setVisible(false);
            taulell.getJlNom06().setVisible(false);
            taulell.getJlNumFitxes06().setVisible(false);
            taulell.getJlTotalFitxes06().setVisible(false);
        } else if (jugador.getPosicioTaula() == 6) {
            taulell.getJlFitxes07().setVisible(false);
            taulell.getJlAvatar07().setVisible(false);
            taulell.getJlNom07().setVisible(false);
            taulell.getJlNumFitxes07().setVisible(false);
            taulell.getJlTotalFitxes07().setVisible(false);
        } else if (jugador.getPosicioTaula() == 7) {
            taulell.getJlFitxes08().setVisible(false);
            taulell.getJlAvatar08().setVisible(false);
            taulell.getJlNom08().setVisible(false);
            taulell.getJlNumFitxes08().setVisible(false);
            taulell.getJlTotalFitxes08().setVisible(false);
        } else if (jugador.getPosicioTaula() == 8) {
            taulell.getJlFitxes09().setVisible(false);
            taulell.getJlAvatar09().setVisible(false);
            taulell.getJlNom09().setVisible(false);
            taulell.getJlNumFitxes09().setVisible(false);
            taulell.getJlTotalFitxes09().setVisible(false);
        }
    }

    //Canvi del nom del boto call a check, i ocultar el boto check per posar-hi un input
    //Fa clicables
    public void actualitzaBotons() {

        if (!(tornActual.getJugadorTorn() instanceof Bot)) {
            taulell.getjSldrEntradaFitxes().setEnabled(true);
            taulell.getJbCallCheck().setEnabled(true);
            taulell.getJbFold().setEnabled(true);
            taulell.getJbRise().setEnabled(true);
            taulell.getjSldrEntradaFitxes().setMinimum(faseActual.getApostaMinima());
            taulell.getjSldrEntradaFitxes().setMaximum(tornActual.getJugadorTorn().getFitxesActuals());
            if (getTornActual().getJugadorTorn().getAposta() != null) {
                if (tornActual.getJugadorTorn().getAposta().getQuantitat() < faseActual.getApostaMinima() - tornActual.getJugadorTorn().getAposta().getQuantitat()) {//Fem que el boto call sigui call
                    taulell.getJbCallCheck().setText("Call: " + (faseActual.getApostaMinima()) + ".");
                } else if (tornActual.getJugadorTorn().getAposta().getQuantitat() == faseActual.getApostaMinima()) {//Fem que el boto call sigui check
                    taulell.getJbCallCheck().setText("Check: ");
                }
            } else {
                taulell.getJbCallCheck().setText("Call2: " + (faseActual.getApostaMinima()));
            }
        } else {
            taulell.getjSldrEntradaFitxes().setEnabled(false);
            taulell.getJbCallCheck().setEnabled(false);
            taulell.getJbFold().setEnabled(false);
            taulell.getJbRise().setEnabled(false);
        }
    }

    //Accio del boto checkcall
    public void accioCheckCall() {
        if (getTornActual().getJugadorTorn().getAposta() != null) {
            if (getFaseActual().getApostaMinima() != getTornActual().getJugadorTorn().getAposta().getQuantitat()) {
                doCall();
            } else {
                doCheck();
            }
        } else {
            doCall();
        }
    }

    public void actualitzarLog(String text) {
        logs += "\n" + text;
        //Jt.setText(logs);
        taulell.getJtLog().setText(logs);
        downScroll(taulell.getJtLog());
    }

    public void downScroll(JTextArea jTlog) {
        Dimension tamanhoTextArea = jTlog.getSize();
        Point p = new Point(0, tamanhoTextArea.height);
        JScrollPane scroll = taulell.getjScrollPaneFrase();
        scroll.getViewport().setViewPosition(p);
    }

    public void gestionarButtons() {
        System.out.println("dealer: " + cp.getDealer() + ", sb: " + cp.getSmallBlind() + ", bb: " + cp.getBigBlind());
        for (int i = 0; i < taulell.getJLButtons().size(); i++) {

            if (i == cp.getDealer()) {
                System.out.println(i + " es el dealer");
                taulell.getJLButtons().get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/dealer.png")));
                taulell.getJLButtons().get(i).setVisible(true);
            } else if (i == cp.getBigBlind()) {
                System.out.println(i + " es el bb");
                taulell.getJLButtons().get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/bigblind.png")));
                taulell.getJLButtons().get(i).setVisible(true);
            } else if (i == cp.getSmallBlind()) {
                System.out.println(i + " es el sb");
                taulell.getJLButtons().get(i).setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/smallblind.png")));
                taulell.getJLButtons().get(i).setVisible(true);
            } else {
                System.out.println(i + " no es res");
                taulell.getJLButtons().get(i).setVisible(false);
            }
        }
    }

    public ControladoraJabber getCjabber() {
        return cjabber;
    }

    public void setCjabber(ControladoraJabber cjabber) {
        this.cjabber = cjabber;
    }

    public ControladoraPartida getCp() {
        return cp;
    }

    public void setCp(ControladoraPartida cp) {
        this.cp = cp;
    }

    public ControladoraPartidaOnline getCpo() {
        return cpo;
    }

    public void setCpo(ControladoraPartidaOnline cpo) {
        this.cpo = cpo;
    }

    public Fase getFaseActual() {
        return faseActual;
    }

    public void setFaseActual(Fase faseActual) {
        this.faseActual = faseActual;
    }

    public Jugador getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(Jugador jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public GuiTaulell getTaulell() {
        return taulell;
    }

    public void setTaulell(GuiTaulell taulell) {
        this.taulell = taulell;
    }

    public Torn getTornActual() {
        return tornActual;
    }

    public void setTornActual(Torn tornActual) {
        this.tornActual = tornActual;
    }
}
