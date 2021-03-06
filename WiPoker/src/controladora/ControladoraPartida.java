
/*
 * ControladoraPartida.java
 */
package controladora;

import domini.Aposta;
import domini.Baralla;
import domini.Bot;
import domini.Carta;
import domini.Fase;
import domini.Jugador;
import domini.Partida;
import domini.Ronda;
import domini.Taula;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartida {

    public Taula taula;
    public Baralla baralla;
    public Partida partida;
    public ControladoraCartes controlCartes = new ControladoraCartes();
    public ControladoraIA controlIA;
    public ControladoraJoc controlJoc = new ControladoraJoc(this);
    public ControladoraGui gui;
    private int smallBlind = 0;
    private int bigBlind = 0;
    private int dealer = 0;

    public ControladoraPartida(ControladoraGui gui) {
        this.gui = gui;
    }

    public ControladoraPartida(int maxJugadors, ControladoraGui gui) {
        this.gui = gui;
        baralla = controlJoc.crearBaralla();
        taula = new Taula(maxJugadors, baralla);
        partida = new Partida(Calendar.getInstance());
        taula.setPartidaActual(partida);
        partida.setJugadors(new ArrayList<Jugador>(maxJugadors));
        controlIA = new ControladoraIA(partida, controlCartes, this);
    }

    /**
     *
     * @return si queden places lliures
     */
    public boolean taulaIsFull() {
        boolean completa = taula.getPlaces() - taula.getCadiresOcupades() == 0;
        return completa;
    }

    public void jugar() throws InterruptedException {
        gui.actualitzarLog("*********NOVA PARTIDA*********");
        gui.ocultarPanellsJugadors();
        gui.gestionarFitxes();
        gui.mostrarAvatars(partida.getJugadors());
        Collections.sort(partida.getJugadors(), new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Jugador j1 = (Jugador) o1;
                Jugador j2 = (Jugador) o2;
                return j1.getPosicioTaula() - j2.getPosicioTaula();
            }
        });
        int boto = 0;
        int numJugadorsActius = 0;
        for (int i = 0; i < partida.getJugadors().size(); i++) {
            if (!partida.getJugadors().get(i).isEliminat()) {
                numJugadorsActius++;
            }
        }
        //mentre quedin jugadors per seguir la partida
        while (numJugadorsActius > 1) {
            iniciarRonda(boto);
            //treiem els jugadors eliminats
            determinarJugadorsEliminats();
            //Avança Boto o el reiniciem si estem a l'ultim jugador
            if (numJugadorsActius == 2) {
                boolean botoTrobat = false;
                for (int i = 0; i < partida.getJugadors().size() && !botoTrobat; i++) {
                    if (i != boto && !partida.getJugadors().get(i).isEliminat()) {
                        boto = i;
                        botoTrobat = true;
                        dealer = i;
                    }
                }
            } else {
                boolean botoTrobat = false;
                for (int i = boto; i < partida.getJugadors().size() && !botoTrobat; i++) {
                    if (i != boto && !partida.getJugadors().get(i).isEliminat()) {
                        boto = i;
                        botoTrobat = true;
                        dealer = i;
                    }
                }
                if (!botoTrobat) {
                    for (int i = 0; i < boto && !botoTrobat; i++) {
                        if (i != boto && !partida.getJugadors().get(i).isEliminat()) {
                            boto = i;
                            botoTrobat = true;
                            dealer = i;
                        }
                    }
                }
            }
            numJugadorsActius = 0;
            for (int i = 0; i < partida.getJugadors().size(); i++) {
                if (!partida.getJugadors().get(i).isEliminat()) {
                    numJugadorsActius++;
                }
            }
        }
    }

    public void iniciarRonda(int boto) throws InterruptedException {
        //Crea ronda
        Ronda novaRonda = new Ronda(0);
        novaRonda.setPartida(partida);
        partida.getRondes().add(novaRonda);
        baralla = controlJoc.crearBaralla();
        controlJoc.barallar(baralla);
        gui.actualitzarLog("\n\n*******NOVA RONDA*******");
        //Inicia Fase
        gui.mostrarAvatars(partida.getJugadors());
        for (int i = 0; i < 4; i++) {
            gui.getFaseActual().setApostaMinima(0);

            Fase novaFase = new Fase(Fase.getNomFases()[Fase.getNumFase()], novaRonda, 0);
            if (i == 0) {
                //possem a 0 l'aposta minima si estem a la primera fase (preflop)
                novaFase.setApostaMinima(0);
            }

            gui.actualitzarLog("\n--" + novaFase.toString() + "--");
            this.gui.setFaseActual(novaFase);
            novaRonda.getFases().add(novaFase);
            gestionarFase(novaFase, boto);
            //reiniciem i/o assignem les apostes dels jugadors
            for (Jugador j : partida.getJugadors()) {
                if (j.getAposta() != null) {
                    j.getAposta().setQuantitat(0);
                } else {
                    j.setAposta(new Aposta(j, 0));
                }
            }
            int numJugadorsFold = 0;
            //controlem els jugadors que han abandoant la ronda(fold)
            for (Jugador j : partida.getJugadors()) {
                if (j.isHaFetFold()) {
                    numJugadorsFold++;
                }
            }
            if (numJugadorsFold == partida.getJugadors().size() - 1) {
                i = 4;
            }

        }
        //comprovem les combinacions de tots els jugadors i repartim fitxes
        determinarCombinacio();
        ArrayList<Jugador> jugadorsGuanyadors = determinarGuanyador();


        controlJoc.repartirPremi(jugadorsGuanyadors, novaRonda.getPot());
        novaRonda.setJugadorGuanyadorRonda((jugadorsGuanyadors));
        //reiniciem fases
        Fase.setNumFase(0);
        //determinarJugadorsEliminats();
        novaRonda.getFases().clear();
        //reiniciem variables de control de la partida de tots els jugadors
        for (Jugador jugador : partida.getJugadors()) {
            jugador.getMaActual().getCartes().clear();
            if (jugador.isHaFetFold()) {
                jugador.setHaFetFold(false);


            } else if (jugador.haFetAllin()) {
                jugador.setHaFetAllin(false);
            }
            //jugador.getAposta().setQuantitat(0.0);
            jugador.setApostaTotalRonda(0);
        }
        //actualitzem la gui
        gui.ocultarCartesComunitaries();
        gui.mostrarMissatge(jugadorsGuanyadors.toString(), "Ha/n guanyat la ronda");
        gui.gestionarFitxes();
    }

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

//Al finalitzar la fase afegir potFase al pot de la ronda
    }

    public void eventsPreFlop(int apostaMin, Fase fase, int boto) throws InterruptedException {
        //creem un torn per a cada jugador
        for (Jugador j : partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }
        int jugadorsActius = 0;
        for (int i = 0; i < partida.getJugadors().size();i++) {
            if (!partida.getJugadors().get(i).isEliminat()) {
                jugadorsActius++;
            }
        }

        if (jugadorsActius == 2) {
            for (int i = 0; i < partida.getJugadors().size(); i++) {
                if (i != boto && !partida.getJugadors().get(i).isEliminat()) {
                    smallBlind = i;
                    controlJoc.ferRaise(partida.getJugadors().get(i), fase, apostaMin, 10);
                    gui.gestionarFitxes();
                }
            }
            for (int i = 0; i < partida.getJugadors().size(); i++) {
                if (i == boto && !partida.getJugadors().get(i).isEliminat()) {
                    bigBlind = i;
                    controlJoc.ferRaise(partida.getJugadors().get(i), fase, apostaMin, 20);
                    gui.gestionarFitxes();
                }
            }

        } else {
            int posicioSmallBlind = 0;
            boolean trobat = false;
            //desde Boto fins lultim jugador
            for (int i = boto; i < partida.getJugadors().size() && !trobat; i++) {
                if (!partida.getJugadors().get(i).isEliminat() && i != boto) {
                    //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                    //controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin);
                    //controlJoc.ferCall(partida.getJugadors().get(i), fase, apostaMin);
                    controlJoc.ferRaise(partida.getJugadors().get(i), fase, apostaMin, 10);
                    posicioSmallBlind = i;
                    smallBlind = i;
                    trobat = true;
                    //actualitzem la gui
                    gui.gestionarPot();
                    gui.gestionarFitxes();
                }
            }
            //Si no toba la Small Blind, del primer jugador al boto
            if (!trobat) {
                for (int i = 0; i < boto && !trobat; i++) {
                    if (!partida.getJugadors().get(i).isEliminat() && i != boto) {
                        //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                        //controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin);
                        //controlJoc.ferCall(partida.getJugadors().get(i), fase, apostaMin);
                        controlJoc.ferRaise(partida.getJugadors().get(i), fase, apostaMin, 10);
                        posicioSmallBlind = i;
                        smallBlind = i;
                        trobat = true;
                        //actualitzem la gui
                        gui.gestionarPot();
                        gui.gestionarFitxes();
                    }
                }
            }
            //Busca la Big Blind igual que la Small Blind
            trobat = false;
            for (int i = posicioSmallBlind; i < partida.getJugadors().size() && !trobat; i++) {
                if (!partida.getJugadors().get(i).isEliminat() && i != boto && posicioSmallBlind != i) {
                    //controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
                    //controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin * 2);
                    controlJoc.ferRaise(partida.getJugadors().get(i), fase, apostaMin, 20);
                    bigBlind = i;
                    trobat = true;
                    gui.gestionarPot();
                    gui.gestionarFitxes();
                }
            }
            if (!trobat) {
                for (int i = 0; i < posicioSmallBlind && !trobat; i++) {
                    if (!partida.getJugadors().get(i).isEliminat() && i != boto && posicioSmallBlind != i) {
                        //controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
                        //controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin * 2);
                        controlJoc.ferRaise(partida.getJugadors().get(i), fase, apostaMin, 20);
                        bigBlind = i;
                        trobat = true;
                        gui.gestionarPot();
                        gui.gestionarFitxes();
                    }
                }
            }
        }

        gui.gestionarButtons();
        //Reparteix cartes privades
        controlJoc.repartirCartesPrivades(partida.getJugadors(), baralla);
        gui.setCartesPrivades();
        //determina la combinacio al Pre-Flop
        determinarCombinacioPreFlop();
        //Fer Apostes
        eventsFase(fase, boto);
    }

    public void eventsFase(Fase fase, int boto) throws InterruptedException {
        boolean fi = false;
        //num vegades que fa la mateixa fase (igualant les apostes)
        int countFase = 0;
        while (!fi) {
            //Mira si hi tothom menys un han fet fold
            int numFold = 0;
            for (Jugador j : partida.getJugadors()) {
                if (j.isHaFetFold() || j.isEliminat() || j.haFetAllin()) {
                    numFold++;
                }
            }
            if (numFold == partida.getJugadors().size() - 1) {
                break;
                //return;
            }

            for (int i = bigBlind + 1; i < partida.getJugadors().size(); i++) {
                //Si no es la primera vegada que el jugador aposta en la fase
                if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).haFetAllin() && !partida.getJugadors().get(i).isEliminat()) {
                    if (countFase > 0 && partida.getJugadors().get(i).getAposta().getQuantitat() != fase.getApostaMinima()) {
                        gui.setTornActual(partida.getJugadors().get(i).getTorn());
                        gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                        gui.actualitzaBotons();
                        if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                            Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                            gui.getTornActual().resume();
                            Thread.sleep(2500);
                            bot.jugadaBot(controlIA, fase, countFase);
                            gui.gestionarPot();
                            gui.getTornActual().run();
                        } else {

                            gui.getTornActual().run();
                        }
                        gui.actualitzaBotons();
                        gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                        gui.gestionarFitxes();
                    } else if (countFase == 0) {
                        gui.setTornActual(partida.getJugadors().get(i).getTorn());
                        gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                        gui.actualitzaBotons();
                        if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().haFetAllin() && !partida.getJugadors().get(i).isEliminat()) {
                            if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                                Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                                gui.getTornActual().resume();
                                Thread.sleep(2500);
                                bot.jugadaBot(controlIA, fase, countFase);
                                gui.getTornActual().run();
                                gui.gestionarPot();
                            } else {
                                gui.getTornActual().run();
                            }
                            gui.actualitzaBotons();
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes();
                        }
                    }
                }
            }

            for (int i = 0; i < bigBlind + 1; i++) {
                //Si no es la primera vegada que el jugador aposta en la fase
                if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).haFetAllin() && !partida.getJugadors().get(i).isEliminat()) {
                    if (countFase > 0 && partida.getJugadors().get(i).getAposta().getQuantitat() != fase.getApostaMinima()) {
                        gui.setTornActual(partida.getJugadors().get(i).getTorn());
                        gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                        gui.actualitzaBotons();
                        if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                            Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                            gui.getTornActual().resume();
                            Thread.sleep(2500);
                            bot.jugadaBot(controlIA, fase, countFase);
                            gui.getTornActual().run();
                            gui.gestionarPot();
                        } else {

                            gui.getTornActual().run();
                        }
                        gui.actualitzaBotons();
                        gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                        gui.gestionarFitxes();
                    } else if (countFase == 0) {
                        gui.setTornActual(partida.getJugadors().get(i).getTorn());
                        gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                        gui.actualitzaBotons();
                        if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().haFetAllin() && !partida.getJugadors().get(i).isEliminat()) {
                            if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                                Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                                gui.getTornActual().resume();
                                Thread.sleep(2500);
                                bot.jugadaBot(controlIA, fase, countFase);
                                gui.getTornActual().run();
                                gui.gestionarPot();
                            } else {

                                gui.getTornActual().run();
                            }
                            gui.actualitzaBotons();
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes();
                        }
                    }
                }
            }

            //Mira SI algun jugador (que no a fet fold) a apostat diferent
            boolean hanApostatDiferent = false;
            for (int i = 0; i < partida.getJugadors().size(); i++) {
                if (!partida.getJugadors().get(i).isHaFetFold()
                        && !partida.getJugadors().get(i).haFetAllin()
                        && !partida.getJugadors().get(i).isEliminat()) {

                    if (!(fase.getApostaMinima() == partida.getJugadors().get(i).getAposta().getQuantitat())) {
                        hanApostatDiferent = true;
                    }
                }
            }
            if (!hanApostatDiferent) {
                fi = true;
            } else {
                for (Jugador j : partida.getJugadors()) {
                    j.setTorn(null);
                }
                for (Jugador j : partida.getJugadors()) {
                    j.setTorn(new Torn(j));
                }
                countFase++;
            }
        }

    }

    public void eventsFlop(Fase fase, int boto) throws InterruptedException {
        controlJoc.cremarCartes(baralla);
        ArrayList<Carta> publiques = controlJoc.aixecarCartes(partida.getJugadors(), baralla, 3);
        gui.mostrarCartesComunitaries(publiques);
        determinarCombinacio();

        for (Jugador j : partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }
        eventsFase(fase, boto);
    }

    public void eventsTurn(Fase fase, int boto) throws InterruptedException {
        controlJoc.cremarCartes(baralla);
        ArrayList<Carta> publiques = controlJoc.aixecarCartes(partida.getJugadors(), baralla, 1);
        gui.mostrarCartesComunitaries(publiques);
        determinarCombinacio();

        for (Jugador j : partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }
        eventsFase(fase, boto);
    }

    public void eventsRiver(Fase fase, int boto) throws InterruptedException {
        controlJoc.cremarCartes(baralla);
        ArrayList<Carta> publiques = controlJoc.aixecarCartes(partida.getJugadors(), baralla, 1);
        gui.mostrarCartesComunitaries(publiques);
        determinarCombinacio();

        for (Jugador j : partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }
        eventsFase(fase, boto);
    }

    public void determinarCombinacio() {
        for (Jugador j : partida.getJugadors()) {
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

    public void determinarCombinacioPreFlop() {
        for (Jugador j : partida.getJugadors()) {
            if (controlCartes.esParella(j)) {
            } else if (controlCartes.valorMesAlt(j)) {
            }
        }
    }

    public ArrayList<Jugador> determinarGuanyador() {
        ArrayList<Jugador> posiblesGuanyadors = new ArrayList<Jugador>();
        int combinacio = 0;
        int valorMesAlt = 0;
        int valorDesempat = 0;
        for (int i = 0; i < partida.getJugadors().size(); i++) {
            //Si el jugado no esta eliminat o no ha fet fold
            if (!partida.getJugadors().get(i).isEliminat() && !partida.getJugadors().get(i).isHaFetFold()) {
                //Si el seu valor de combinacio es mes alt que lactual
                if (partida.getJugadors().get(i).getMaActual().getCombinacio() > combinacio) {
                    //guarda: combinacio, valor mes alt i valor desepat
                    combinacio = partida.getJugadors().get(i).getMaActual().getCombinacio();
                    valorMesAlt = partida.getJugadors().get(i).getMaActual().getValorMesAlt();
                    valorDesempat = partida.getJugadors().get(i).getMaActual().getValorDesempat();
                    //si la seva combinacio es igual a l'actual
                } else if (partida.getJugadors().get(i).getMaActual().getCombinacio() == combinacio) {
                    //Mira si te un valor mes alt
                    if (partida.getJugadors().get(i).getMaActual().getValorMesAlt() > valorMesAlt) {
                        //si te un valor mes alt guarda el valor mes alt i el valor de desempat
                        valorMesAlt = partida.getJugadors().get(i).getMaActual().getValorMesAlt();
                        valorDesempat = partida.getJugadors().get(i).getMaActual().getValorDesempat();
                        //Si te un valor mes alt igual
                    } else if (partida.getJugadors().get(i).getMaActual().getValorMesAlt() == valorMesAlt) {
                        //Mira si te un valor de desempat mes alt
                        if (partida.getJugadors().get(i).getMaActual().getValorDesempat() > valorDesempat) {
                            //Si el te guarda el nou valor de desempat
                            valorDesempat = partida.getJugadors().get(i).getMaActual().getValorDesempat();
                        }
                    }
                }
            }
        }
        for (int i = 0; i < partida.getJugadors().size(); i++) {
            if (partida.getJugadors().get(i).getMaActual().getCombinacio() == combinacio && partida.getJugadors().get(i).getMaActual().getValorMesAlt() == valorMesAlt && partida.getJugadors().get(i).getMaActual().getValorDesempat() == valorDesempat && !partida.getJugadors().get(i).isEliminat() && !partida.getJugadors().get(i).isHaFetFold()) {
                posiblesGuanyadors.add(partida.getJugadors().get(i));
                gui.actualitzarLog("Guanyador " + i + ": " + partida.getJugadors().get(i).getAlias());
            }
        }

        return posiblesGuanyadors;
    }

    public void determinarJugadorsEliminats() {
        for (Jugador j : partida.getJugadors()) {
            if (j.getFitxesActuals() <= 0) {
                j.setEliminat(true);
                gui.gestionarJugadorEliminat(j);
                gui.actualitzarLog("Eliminant: " + j.getAlias());
            }
        }
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public int getDealer() {
        return dealer;
    }
}
