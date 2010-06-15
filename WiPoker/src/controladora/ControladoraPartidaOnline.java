/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * prova
 */
package controladora;

import domini.Aposta;
import domini.Carta;
import domini.Fase;
import domini.Jugador;
import domini.Ma;
import domini.Ronda;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import sockets.Servidor;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartidaOnline extends ControladoraPartida {

    private int dealer = 0;
    private int smallBlind = 0;
    private int bigBlind = 0;
    private Servidor servidor;

    public ControladoraPartidaOnline(ControladoraGui gui) {
        super(gui);
        this.gui = gui;
    }

    public ControladoraPartidaOnline(int maxJugadors, ControladoraGui gui, final String ip, final int port, final Jugador jugador) {
        super(maxJugadors, gui);
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }
    
    public void afegirJugador(Jugador nouJugador) {
        if (!taulaIsFull()) {
            nouJugador.setMaActual(new Ma());
            super.partida.getJugadors().add(nouJugador);
            super.taula.setCadiresOcupades((super.taula.getCadiresOcupades() + 1));
        }
    }

    public void afegirJugadorOnline() {
        Jugador j = servidor.afegirJugadorRemot();
        partida.getJugadors().add(j);
    }

    @Override
    public void jugar() throws InterruptedException {
        System.out.println("---------********NOVA PARTIDA******---------");
        gui.actualitzarLog("*********NOVA PARTIDA*********");
        gui.ocultarPanellsJugadors();
        gui.gestionarFitxes();
        gui.mostrarAvatars(gui.getCp().partida.getJugadors());
        Collections.sort(gui.getCp().partida.getJugadors(), new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Jugador j1 = (Jugador) o1;
                Jugador j2 = (Jugador) o2;
                return j1.getPosicioTaula() - j2.getPosicioTaula();
            }
        });
        int boto = 0;
        int numJugadorsActius = 0;
        for (int i = 0; i < gui.getCp().partida.getJugadors().size(); i++) {
            if (!gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                numJugadorsActius++;
            }
        }
        while (numJugadorsActius > 1) {
            iniciarRonda(boto);
            determinarJugadorsEliminats();
            //Avança Boto
            if (boto == gui.getCp().partida.getJugadors().size()) {
                boto = 0;
                if (gui.getCp().partida.getJugadors().get(boto).isEliminat() == true) {
                    boolean botoTrobat = false;
                    for (int i = boto + 1; i < gui.getCp().partida.getJugadors().size() && !botoTrobat; i++) {
                        if (!gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                            boto = i;
                            botoTrobat = true;
                            dealer = i;
                        }
                    }
                }
            } else {
                boolean botoTrobat = false;
                for (int i = boto + 1; i < gui.getCp().partida.getJugadors().size() && !botoTrobat; i++) {
                    if (!gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                        boto = i;
                        botoTrobat = true;
                        dealer = i;
                    }
                }
            }
            numJugadorsActius = 0;
            for (int i = 0; i < gui.getCp().partida.getJugadors().size(); i++) {
                if (!gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                    numJugadorsActius++;
                }
            }
        }
    }

    @Override
    public void iniciarRonda(int boto) throws InterruptedException {
        //Crea ronda
        Ronda novaRonda = new Ronda(0);
        novaRonda.setPartida(gui.getCp().partida);
        gui.getCp().partida.getRondes().add(novaRonda);
        baralla = controlJoc.crearBaralla();
        controlJoc.barallar(baralla);
        System.out.println("\n\n\n\n\n********************NOVA RONDA****************************");
        gui.actualitzarLog("\n\n*******NOVA RONDA*******");
        //Inicia Fase
        for (int i = 0; i < 4; i++) {
            gui.getFaseActual().setApostaMinima(0);

            Fase novaFase = new Fase(Fase.getNomFases()[Fase.getNumFase()], novaRonda, 0);
            if (i == 0) {
                novaFase.setApostaMinima(0);
            }

            System.out.println(novaFase.toString());
            gui.actualitzarLog("\n--" + novaFase.toString() + "--");
            this.gui.setFaseActual(novaFase);
            novaRonda.getFases().add(novaFase);
            gestionarFase(novaFase, boto);
            for (Jugador j : gui.getCp().partida.getJugadors()) {
                if (j.getAposta() != null) {
                    j.getAposta().setQuantitat(0);
                } else {
                    j.setAposta(new Aposta(j, 0));
                }
            }
            int numJugadorsFold = 0;
            for (Jugador j : gui.getCp().partida.getJugadors()) {
                if (j.isHaFetFold()) {
                    numJugadorsFold++;
                }
            }
            if (numJugadorsFold == gui.getCp().partida.getJugadors().size() - 1) {
                i = 4;
            }
        }
    }

    @Override
    public void gestionarFase(Fase novaFase, int boto) throws InterruptedException {
        //Clase fase te dos static: array string nom fases i byte amb el numero de fase
        //Passem al constructor l'string de l'index de la fase
        if (Fase.getNumFase() == 1) {
            eventsPreFlop(0, novaFase, boto);
        } else if (Fase.getNumFase() == 2) {
            eventsFlop(novaFase, boto);
        } else if (Fase.getNumFase() == 3) {
            eventsTurn(novaFase, boto);
        } else if (Fase.getNumFase() == 4) {
            eventsRiver(novaFase, boto);
        }
    }

    @Override
    public void eventsPreFlop(int apostaMin, Fase fase, int boto) throws InterruptedException {
        for (Jugador j : gui.getCp().partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }
        int posicioSmallBlind = 0;
        boolean trobat = false;
        //desde Boto fins lultim jugador
        for (int i = boto; i < gui.getCp().partida.getJugadors().size() && !trobat; i++) {
            if (!gui.getCp().partida.getJugadors().get(i).isEliminat() && i != boto) {
                //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                //controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin);
                //controlJoc.ferCall(partida.getJugadors().get(i), fase, apostaMin);
                controlJoc.ferRaise(gui.getCp().partida.getJugadors().get(i), fase, apostaMin, 10);
                posicioSmallBlind = i;
                smallBlind = i;
                trobat = true;
                gui.gestionarPot();
                gui.gestionarFitxes();
            }
        }
        //Si no toba la Small Blind, del primer jugador al boto
        if (!trobat) {
            for (int i = 0; i < boto && !trobat; i++) {
                if (!gui.getCp().partida.getJugadors().get(i).isEliminat() && i != boto) {
                    //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                    //controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin);
                    //controlJoc.ferCall(partida.getJugadors().get(i), fase, apostaMin);
                    controlJoc.ferRaise(gui.getCp().partida.getJugadors().get(i), fase, apostaMin, 10);
                    posicioSmallBlind = i;
                    smallBlind = i;
                    trobat = true;
                    gui.gestionarPot();
                    gui.gestionarFitxes();
                }
            }
        }
        //Busca la Big Blind igual que la Small Blind
        trobat = false;
        for (int i = posicioSmallBlind; i < gui.getCp().partida.getJugadors().size() && !trobat; i++) {
            if (!gui.getCp().partida.getJugadors().get(i).isEliminat() && i != boto && posicioSmallBlind != i) {
                //controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
                //controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin * 2);
                controlJoc.ferRaise(gui.getCp().partida.getJugadors().get(i), fase, apostaMin, 20);
                bigBlind = i;
                trobat = true;
                gui.gestionarPot();
                gui.gestionarFitxes();
            }
        }
        if (!trobat) {
            for (int i = 0; i < posicioSmallBlind && !trobat; i++) {
                if (!gui.getCp().partida.getJugadors().get(i).isEliminat() && i != boto && posicioSmallBlind != i) {
                    //controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
                    //controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin * 2);
                    controlJoc.ferRaise(gui.getCp().partida.getJugadors().get(i), fase, apostaMin, 20);
                    bigBlind = i;
                    trobat = true;
                    gui.gestionarPot();
                    gui.gestionarFitxes();
                }
            }
        }
        gui.gestionarButtons();
        //Reparteix cartes privades
        controlJoc.repartirCartesPrivades(gui.getCp().partida.getJugadors(), baralla);
        gui.setCartesPrivades();
        //determina la combinacio al Pre-Flop
        determinarCombinacioPreFlop();
        //Fer Apostes
        eventsFase(fase, boto);
    }

    @Override
    public void eventsFase(Fase fase, int boto) throws InterruptedException {
        boolean fi = false;
        //num vegades que fa la mateixa fase (igualant les apostes)
        int countFase = 0;
        while (!fi) {
            //Mira si hi tothom menys un han fet fold
            int numFold = 0;
            for (Jugador j : gui.getCp().partida.getJugadors()) {
                if (j.isHaFetFold()) {
                    numFold++;
                }
            }
            if (numFold == gui.getCp().partida.getJugadors().size() - 1) {
                break;
                //return;
            }

            for (int i = bigBlind + 1; i < gui.getCp().partida.getJugadors().size(); i++) {
                //Si no es la primera vegada que el jugador aposta en la fase
                if (!gui.getCp().partida.getJugadors().get(i).isHaFetFold() && !gui.getCp().partida.getJugadors().get(i).HaFetAllin() && !gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                    if (countFase > 0 && gui.getCp().partida.getJugadors().get(i).getAposta().getQuantitat() != fase.getApostaMinima()) {
                        gui.setTornActual(gui.getCp().partida.getJugadors().get(i).getTorn());
                        gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                        gui.actualitzaBotons();
                        gui.getTornActual().run();
                        gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                        gui.gestionarFitxes();
                    } else if (countFase == 0) {
                        gui.setTornActual(gui.getCp().partida.getJugadors().get(i).getTorn());
                        gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                        gui.actualitzaBotons();
                        if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().HaFetAllin() && !gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                            gui.getTornActual().run();
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes();
                        }
                    }
                }
            }

            for (int i = 0; i < bigBlind + 1; i++) {
                //Si no es la primera vegada que el jugador aposta en la fase
                if (!gui.getCp().partida.getJugadors().get(i).isHaFetFold() && !gui.getCp().partida.getJugadors().get(i).HaFetAllin() && !gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                    if (countFase > 0 && gui.getCp().partida.getJugadors().get(i).getAposta().getQuantitat() != fase.getApostaMinima()) {
                        gui.setTornActual(gui.getCp().partida.getJugadors().get(i).getTorn());
                        gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                        gui.actualitzaBotons();
                        gui.getTornActual().run();
                        gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                        gui.gestionarFitxes();
                    } else if (countFase == 0) {
                        gui.setTornActual(gui.getCp().partida.getJugadors().get(i).getTorn());
                        gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                        gui.actualitzaBotons();
                        if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().HaFetAllin() && !gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                            gui.getTornActual().run();
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes();
                        }
                    }
                }
            }

            //Mira SI algun jugador (que no a fet fold) a apostat diferent
            boolean hanApostatDiferent = false;
            for (int i = 0; i < gui.getCp().partida.getJugadors().size(); i++) {
                if (!gui.getCp().partida.getJugadors().get(i).isHaFetFold() && !gui.getCp().partida.getJugadors().get(i).HaFetAllin() && !gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                    if (!(fase.getApostaMinima() == gui.getCp().partida.getJugadors().get(i).getAposta().getQuantitat())) {
                        hanApostatDiferent = true;
                    }
                }
            }
            if (!hanApostatDiferent) {
                fi = true;
            } else {
                for (Jugador j : gui.getCp().partida.getJugadors()) {
                    j.setTorn(null);
                }
                for (Jugador j : gui.getCp().partida.getJugadors()) {
                    j.setTorn(new Torn(j));
                }
                countFase++;
            }
        }

        System.out.println("Pot del " + fase.getNomFase() + ": " + fase.getRonda().getPot());
    }

    @Override
    public void eventsFlop(Fase fase, int boto) throws InterruptedException {
        controlJoc.cremarCartes(baralla);
        ArrayList<Carta> publiques = controlJoc.aixecarCartes(gui.getCp().partida.getJugadors(), baralla, 3);
        gui.mostrarCartesComunitaries(publiques);
        determinarCombinacio();

        for (Jugador j : gui.getCp().partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }
        eventsFase(fase, boto);
    }

    @Override
    public void eventsTurn(Fase fase, int boto) throws InterruptedException {
        controlJoc.cremarCartes(baralla);
        ArrayList<Carta> publiques = controlJoc.aixecarCartes(gui.getCp().partida.getJugadors(), baralla, 1);
        gui.mostrarCartesComunitaries(publiques);
        determinarCombinacio();

        for (Jugador j : gui.getCp().partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }
        eventsFase(fase, boto);
    }

    @Override
    public void eventsRiver(Fase fase, int boto) throws InterruptedException {
        controlJoc.cremarCartes(baralla);
        ArrayList<Carta> publiques = controlJoc.aixecarCartes(gui.getCp().partida.getJugadors(), baralla, 1);
        gui.mostrarCartesComunitaries(publiques);
        determinarCombinacio();

        for (Jugador j : gui.getCp().partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }
        eventsFase(fase, boto);
        for (Jugador j : gui.getCp().partida.getJugadors()) {
            System.out.println("El " + j.getAlias());
            System.out.println(j.getMaActual());
        }
    }

    @Override
    public void determinarCombinacio() {
        for (Jugador j : gui.getCp().partida.getJugadors()) {
            if (controlCartes.esEscalaReial(j)) {
            } else if (controlCartes.esEscalaColor(j)) {
            } else if (controlCartes.esPoker(j)) {
            } else if (controlCartes.esFull(j)) {
            } else if (controlCartes.sonMateixColor(j)) {
            } else if (controlCartes.esEscala(j)) {
            } else if (controlCartes.esTrio(j)) {
            } else if (controlCartes.esDobleParella(j)) {
            } else if (controlCartes.esParella(j)) {
            } else if (controlCartes.valorMesAlt(j)) {
            }
        }
    }

    @Override
    public void determinarCombinacioPreFlop() {
        for (Jugador j : gui.getCp().partida.getJugadors()) {
            if (controlCartes.esParella(j)) {
            } else if (controlCartes.valorMesAlt(j)) {
            }
        }
    }

    @Override
    public ArrayList<Jugador> determinarGuanyador() {
        ArrayList<Jugador> posiblesGuanyadors = new ArrayList<Jugador>();
        int combinacio = 0;
        int valorMesAlt = 0;
        int valorDesempat = 0;
        for (int i = 0; i < gui.getCp().partida.getJugadors().size(); i++) {
            //Si el jugado no esta eliminat o no ha fet fold
            if (!gui.getCp().partida.getJugadors().get(i).isEliminat() && !gui.getCp().partida.getJugadors().get(i).isHaFetFold()) {
                //Si el seu valor de combinacio es mes alt que lactual
                if (gui.getCp().partida.getJugadors().get(i).getMaActual().getCombinacio() > combinacio) {
                    //guarda: combinacio, valor mes alt i valor desepat
                    combinacio = gui.getCp().partida.getJugadors().get(i).getMaActual().getCombinacio();
                    valorMesAlt = gui.getCp().partida.getJugadors().get(i).getMaActual().getValorMesAlt();
                    valorDesempat = gui.getCp().partida.getJugadors().get(i).getMaActual().getValorDesempat();
                    //si la seva combinacio es igual a l'actual
                } else if (gui.getCp().partida.getJugadors().get(i).getMaActual().getCombinacio() == combinacio) {
                    //Mira si te un valor mes alt
                    if (gui.getCp().partida.getJugadors().get(i).getMaActual().getValorMesAlt() > valorMesAlt) {
                        //si te un valor mes alt guarda el valor mes alt i el valor de desempat
                        valorMesAlt = gui.getCp().partida.getJugadors().get(i).getMaActual().getValorMesAlt();
                        valorDesempat = gui.getCp().partida.getJugadors().get(i).getMaActual().getValorDesempat();
                        //Si te un valor mes alt igual
                    } else if (gui.getCp().partida.getJugadors().get(i).getMaActual().getValorMesAlt() == valorMesAlt) {
                        //Mira si te un valor de desempat mes alt
                        if (gui.getCp().partida.getJugadors().get(i).getMaActual().getValorDesempat() > valorDesempat) {
                            //Si el te guarda el nou valor de desempat
                            valorDesempat = gui.getCp().partida.getJugadors().get(i).getMaActual().getValorDesempat();
                        }
                    }
                }
            }
        }
        for (int i = 0; i < gui.getCp().partida.getJugadors().size(); i++) {
            if (gui.getCp().partida.getJugadors().get(i).getMaActual().getCombinacio() == combinacio && gui.getCp().partida.getJugadors().get(i).getMaActual().getValorMesAlt() == valorMesAlt && gui.getCp().partida.getJugadors().get(i).getMaActual().getValorDesempat() == valorDesempat && !gui.getCp().partida.getJugadors().get(i).isEliminat() && !gui.getCp().partida.getJugadors().get(i).isHaFetFold()) {
                posiblesGuanyadors.add(gui.getCp().partida.getJugadors().get(i));
                System.out.println("Guanyador " + i + ": " + gui.getCp().partida.getJugadors().get(i).getAlias());
                gui.actualitzarLog("Guanyador " + i + ": " + gui.getCp().partida.getJugadors().get(i).getAlias());
            }
        }

        return posiblesGuanyadors;
    }

    @Override
    public void determinarJugadorsEliminats() {
        System.out.println("ELIMINANT JUGADORS...");
        System.out.println("Hi han " + gui.getCp().partida.getJugadors().size() + " jugadors");
        for (Jugador j : gui.getCp().partida.getJugadors()) {
            System.out.print("comprovant el jugador " + j.getAlias() + " amb " + j.getFitxesActuals());
            if (j.getFitxesActuals() <= 0) {
                System.out.println(" ELIMINAR!!!");
                j.setEliminat(true);
                gui.gestionarJugadorEliminat(j);
                gui.actualitzarLog("Eliminant: " + j.getAlias());
            } else {
                System.out.println(" SALVAR!!!");
            }
        }
        System.out.println("");
    }

    @Override
    public int getBigBlind() {
        return bigBlind;
    }

    @Override
    public int getSmallBlind() {
        return smallBlind;
    }

    @Override
    public int getDealer() {
        return dealer;
    }
}

 
