/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    public ControladoraJoc controlJoc = new ControladoraJoc();
    public ControladoraGui gui;

    public ControladoraPartida(int maxJugadors, ControladoraGui gui) {
        this.gui = gui;
        baralla = controlJoc.crearBaralla();
        taula = new Taula(maxJugadors, baralla);
        partida = new Partida(Calendar.getInstance());

        taula.setPartidaActual(partida);
        partida.setJugadors(new ArrayList<Jugador>(maxJugadors));
        controlIA = new ControladoraIA(partida, controlCartes, this);
    }

    public boolean taulaIsFull() {
        boolean completa = taula.getPlaces() - taula.getCadiresOcupades() == 0;
        return completa;
    }

    public void jugar() throws InterruptedException {
        System.out.println("---------********NOVA PARTIDA******---------");
        gui.ocultarPanellsJugadors();
        gui.gestionarFitxes(partida.getJugadors());
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
        int numJugadorsActius = partida.getJugadors().size();
        while (numJugadorsActius > 1) {
            System.out.println("Tamany array jugadors: " + partida.getJugadors().size());
            
            
            iniciarRonda(boto);
            determinarJugadorsEliminats();
            System.out.println("Tamany array jugadors: " + partida.getJugadors().size());
            //Avança Boto
            if (boto == partida.getJugadors().size()) {
                boto = 0;
                if (partida.getJugadors().get(boto).isEliminat() == true) {
                    boolean botoTrobat = false;
                    for (int i = boto + 1; i < partida.getJugadors().size() && !botoTrobat; i++) {
                        if (!partida.getJugadors().get(i).isEliminat()) {
                            boto = i;
                            botoTrobat = true;
                        }
                    }
                }
            } else {
                boolean botoTrobat = false;
                for (int i = boto + 1; i < partida.getJugadors().size() && !botoTrobat; i++) {
                    if (!partida.getJugadors().get(i).isEliminat()) {
                        boto = i;
                        botoTrobat = true;
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
        System.out.println("\n\n\n\n\n********************NOVA RONDA****************************");
        //Inicia Fase
        for (int i = 0; i < 4; i++) {
            gui.getFaseActual().setApostaMinima(0);

            Fase novaFase = new Fase(Fase.getNomFases()[Fase.getNumFase()], novaRonda, 0);
            if (i == 0) {
                novaFase.setApostaMinima(20);
            }

            System.out.println(novaFase.toString());
            this.gui.setFaseActual(novaFase);
            novaRonda.getFases().add(novaFase);
            gestionarFase(novaFase, boto);
            for (Jugador j : partida.getJugadors()) {
                if (j.getAposta() != null) {
                    j.getAposta().setQuantitat(0);
                } else {
                    j.setAposta(new Aposta(j, 0));
                }
            }
            int numJugadorsFold = 0;
            for (Jugador j : partida.getJugadors()) {
                if (j.isHaFetFold()) {
                    numJugadorsFold++;
                }
            }
            if (numJugadorsFold == partida.getJugadors().size() - 1) {
                i = 4;
            }

        }
        determinarCombinacio();
        ArrayList<Jugador> jugadorsGuanyadors = determinarGuanyador();

        System.out.println("POT RONDA: " + novaRonda.getPot());
        controlJoc.repartirPremi(jugadorsGuanyadors, novaRonda.getPot());
        novaRonda.setJugadorGuanyadorRonda((jugadorsGuanyadors));

        Fase.setNumFase(0);
        //determinarJugadorsEliminats();
        novaRonda.getFases().clear();

        for (Jugador jugador : partida.getJugadors()) {
            jugador.getMaActual().getCartes().clear();
            if (jugador.isHaFetFold()) {
                jugador.setHaFetFold(false);


            } else if (jugador.isAllin()) {
                jugador.setHaFetAllin(false);
            }
            //jugador.getAposta().setQuantitat(0.0);
        }
        gui.ocultarCartesComunitaries();
        gui.mostrarMissatge(jugadorsGuanyadors.toString(), "Ha/n guanyat la ronda");
        gui.gestionarFitxes(partida.getJugadors());
    }

    public void gestionarFase(Fase novaFase, int boto) throws InterruptedException {
        //Clase fase te dos static: array string nom fases i byte amb el numero de fase
        //Passem al constructor l'string de l'index de la fase
        if (Fase.getNumFase() == 1) {
            eventsPreFlop(novaFase.getApostaMinima(), novaFase, boto);
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
        for (Jugador j : partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }
        if (partida.getJugadors().size() <= 2) {
            //controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
            controlJoc.ferBlind(partida.getJugadors().get(boto), fase, apostaMin);
            boolean trobat1 = false;
            for (int i = 0; i < partida.getJugadors().size() && !trobat1; i++) {
                if (!partida.getJugadors().get(i).isEliminat() && i != boto) {
                    //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                    controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin / 2);
                    trobat1 = true;
                }
            }
        } else {
            if (boto == partida.getJugadors().size() - 1 || boto == partida.getJugadors().size() - 2) {
                //Boto a la ultima pos
                if (boto == partida.getJugadors().size() - 1) {
                    int posicioSmallBlind = 0;
                    boolean trobat2 = false;
                    for (int i = 0; i < partida.getJugadors().size() && !trobat2; i++) {
                        if (!partida.getJugadors().get(i).isEliminat() && i != boto) {
                            //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                            controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin / 2);
                            posicioSmallBlind = i;
                            trobat2 = true;
                        }
                    }
                    trobat2 = false;
                    for (int i = posicioSmallBlind + 1; i < partida.getJugadors().size() && !trobat2; i++) {
                        if (!partida.getJugadors().get(i).isEliminat() && i != boto && posicioSmallBlind != i) {
                            //controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
                            controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin);
                            trobat2 = true;
                        }
                    }
                } else if (boto == partida.getJugadors().size() - 2) {
                    if (!partida.getJugadors().get(boto + 1).isEliminat()) {
                        //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                        controlJoc.ferBlind(partida.getJugadors().get(boto + 1), fase, apostaMin / 2);
                    } else {
                        int posicioSmallBlind = 0;
                        boolean trobat3 = false;
                        for (int i = 0; i < partida.getJugadors().size() && !trobat3; i++) {
                            if (!partida.getJugadors().get(i).isEliminat() && i != boto) {
                                //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                                controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin / 2);
                                posicioSmallBlind = i;
                                trobat3 = true;
                            }
                        }
                        trobat3 = false;
                        for (int i = posicioSmallBlind + 1; i < partida.getJugadors().size() && !trobat3; i++) {
                            if (!partida.getJugadors().get(i).isEliminat() && i != boto && posicioSmallBlind != i) {
                                //controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
                                controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin);
                                trobat3 = true;
                            }
                        }
                    }
                }

            } else {
                System.out.println("boto: " + boto);
                int posicioSmallBlind = 0;
                boolean trobat3 = false;
                for (int i = boto + 1; i < partida.getJugadors().size() && !trobat3; i++) {
                    if (!partida.getJugadors().get(i).isEliminat() && i != boto) {
                        //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                        controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin / 2);
                        posicioSmallBlind = i;
                        trobat3 = true;
                    }
                }
                if (!trobat3) {
                    for (int i = 0; i < boto && !trobat3; i++) {
                        if (!partida.getJugadors().get(i).isEliminat() && i != boto) {
                            //controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                            controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin / 2);
                            posicioSmallBlind = i;
                            trobat3 = true;
                        }
                    }
                }
                trobat3 = false;
                for (int i = posicioSmallBlind; i < partida.getJugadors().size() && !trobat3; i++) {
                    if (!partida.getJugadors().get(i).isEliminat() && i != boto && posicioSmallBlind != i) {
                        //controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
                        controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin);
                        trobat3 = true;
                    }
                }

                if (!trobat3) {
                    for (int i = 0; i < boto && !trobat3; i++) {
                        if (!partida.getJugadors().get(i).isEliminat() && i != boto && posicioSmallBlind != i) {
                            //controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
                            controlJoc.ferBlind(partida.getJugadors().get(i), fase, apostaMin);
                            trobat3 = true;
                        }
                    }
                }
            }
        }
        controlJoc.repartirCartesPrivades(partida.getJugadors(), baralla);
        gui.setCartesPrivades();
        determinarCombinacioPreFlop();
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
                if (j.isHaFetFold()) {
                    numFold++;
                }
            }
            if (numFold == partida.getJugadors().size() - 1) {
                break;
                //return;
            }
            //APOSTES JUGADORS
            if ((boto + 3) < partida.getJugadors().size()) {
                //Primer bucle
                for (int i = boto + 3; i < partida.getJugadors().size(); i++) {
                    //Si no es la primera vegada que el jugador aposta en la fase
                    if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).isAllin() && !partida.getJugadors().get(i).isEliminat()) {
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
                            } else {
                                gui.getTornActual().run();
                            }
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes(partida.getJugadors());
                        } else if (countFase == 0) {
                            gui.setTornActual(partida.getJugadors().get(i).getTorn());
                            gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                            gui.actualitzaBotons();
                            if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().isAllin() && !partida.getJugadors().get(i).isEliminat()) {
                                if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                                    Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                                    gui.getTornActual().resume();
                                    Thread.sleep(2500);
                                    bot.jugadaBot(controlIA, fase, countFase);
                                    gui.getTornActual().run();
                                } else {
                                    gui.getTornActual().run();
                                }
                                gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                                gui.gestionarFitxes(partida.getJugadors());
                            }
                        }
                    }
                }
                //Mira si hi tothom menys un han fet fold
                numFold = 0;
                for (Jugador j : partida.getJugadors()) {
                    if (j.isHaFetFold()) {
                        numFold++;
                    }
                }
                if (numFold == partida.getJugadors().size() - 1) {
                    break;
                }
                //Segon bucle
                for (int i = 0; i < boto + 3; i++) {
                    //Si no es la primera vegada que el jugador aposta en la fase
                    if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).isAllin() && !partida.getJugadors().get(i).isEliminat()) {
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
                            } else {
                                gui.getTornActual().run();
                            }
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes(partida.getJugadors());

                        } else if (countFase == 0) {
                            gui.setTornActual(partida.getJugadors().get(i).getTorn());
                            gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                            gui.actualitzaBotons();
                            if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().isAllin() && !partida.getJugadors().get(i).isEliminat()) {
                                if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                                    Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                                    gui.getTornActual().resume();
                                    Thread.sleep(2500);
                                    bot.jugadaBot(controlIA, fase, countFase);
                                    gui.getTornActual().run();
                                } else {
                                    gui.getTornActual().run();
                                }
                                gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                                gui.gestionarFitxes(partida.getJugadors());
                            }
                        }
                    }
                }
            } else if ((boto + 3) == partida.getJugadors().size() + 1 || (boto + 3 == partida.getJugadors().size())) {
                //Primer bucle
                for (int i = 0; i < partida.getJugadors().size(); i++) {
                    //Si no es la primera vegada que el jugador aposta en la fase
                    if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).isAllin() && !partida.getJugadors().get(i).isEliminat()) {
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
                            } else {
                                gui.getTornActual().run();
                            }
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes(partida.getJugadors());
                        } else if (countFase == 0) {
                            gui.setTornActual(partida.getJugadors().get(i).getTorn());
                            gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                            gui.actualitzaBotons();
                            if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().isAllin() && !partida.getJugadors().get(i).isEliminat()) {
                                if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                                    Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                                    gui.getTornActual().resume();
                                    Thread.sleep(2500);
                                    bot.jugadaBot(controlIA, fase, countFase);
                                    gui.getTornActual().run();
                                } else {
                                    gui.getTornActual().run();
                                }
                                gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                                gui.gestionarFitxes(partida.getJugadors());
                            }
                        }
                    }
                }
                //Mira si hi tothom menys un han fet fold
                numFold = 0;
                for (Jugador j : partida.getJugadors()) {
                    if (j.isHaFetFold()) {
                        numFold++;
                    }
                }
                if (numFold == partida.getJugadors().size() - 1) {
                    break;
                }
            } else if ((boto + 3) == partida.getJugadors().size() + 2) {
                //Primer bucle
                for (int i = 1; i < partida.getJugadors().size(); i++) {
                    //Si no es la primera vegada que el jugador aposta en la fase
                    if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).isAllin() && !partida.getJugadors().get(i).isEliminat()) {
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
                            } else {
                                gui.getTornActual().run();
                            }
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes(partida.getJugadors());
                        } else if (countFase == 0) {
                            gui.setTornActual(partida.getJugadors().get(i).getTorn());
                            gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                            gui.actualitzaBotons();
                            if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().isAllin() && !partida.getJugadors().get(i).isEliminat()) {
                                if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                                    Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                                    gui.getTornActual().resume();
                                    Thread.sleep(2500);
                                    bot.jugadaBot(controlIA, fase, countFase);
                                    gui.getTornActual().run();
                                } else {
                                    gui.getTornActual().run();
                                }
                                gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                                gui.gestionarFitxes(partida.getJugadors());
                            }
                        }
                    }
                }
                //Mira si hi tothom menys un han fet fold
                numFold = 0;
                for (Jugador j : partida.getJugadors()) {
                    if (j.isHaFetFold()) {
                        numFold++;
                    }
                }
                if (numFold == partida.getJugadors().size() - 1) {
                    break;
                }
                //Segon bucle
                for (int i = 0; i < 2; i++) {
                    //Si no es la primera vegada que el jugador aposta en la fase
                    if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).isAllin() && !partida.getJugadors().get(i).isEliminat()) {
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
                            } else {
                                gui.getTornActual().run();
                            }
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes(partida.getJugadors());

                        } else if (countFase == 0) {
                            gui.setTornActual(partida.getJugadors().get(i).getTorn());
                            gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                            gui.actualitzaBotons();
                            if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().isAllin() && !partida.getJugadors().get(i).isEliminat()) {
                                if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                                    Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                                    gui.getTornActual().resume();
                                    Thread.sleep(2500);
                                    bot.jugadaBot(controlIA, fase, countFase);
                                    gui.getTornActual().run();
                                } else {
                                    gui.getTornActual().run();
                                }
                                gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                                gui.gestionarFitxes(partida.getJugadors());
                            }
                        }
                    }
                }
            } else if ((boto + 3) == partida.getJugadors().size() + 3) {
                //Primer bucle
                for (int i = 3; i < partida.getJugadors().size(); i++) {
                    //Si no es la primera vegada que el jugador aposta en la fase
                    if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).isAllin() && !partida.getJugadors().get(i).isEliminat()) {
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
                            } else {
                                gui.getTornActual().run();
                            }
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes(partida.getJugadors());
                        } else if (countFase == 0) {
                            gui.setTornActual(partida.getJugadors().get(i).getTorn());
                            gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                            gui.actualitzaBotons();
                            if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().isAllin() && !partida.getJugadors().get(i).isEliminat()) {
                                if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                                    Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                                    gui.getTornActual().resume();
                                    Thread.sleep(2500);
                                    bot.jugadaBot(controlIA, fase, countFase);
                                    gui.getTornActual().run();
                                } else {
                                    gui.getTornActual().run();
                                }
                                gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                                gui.gestionarFitxes(partida.getJugadors());
                            }
                        }
                    }
                }
                //Mira si hi tothom menys un han fet fold
                numFold = 0;
                for (Jugador j : partida.getJugadors()) {
                    if (j.isHaFetFold()) {
                        numFold++;
                    }
                }
                if (numFold == partida.getJugadors().size() - 1) {
                    break;
                }
                //Segon bucle
                for (int i = 0; i < 3; i++) {
                    //Si no es la primera vegada que el jugador aposta en la fase
                    if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).isAllin() && !partida.getJugadors().get(i).isEliminat()) {
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
                            } else {
                                gui.getTornActual().run();
                            }
                            gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                            gui.gestionarFitxes(partida.getJugadors());

                        } else if (countFase == 0) {
                            gui.setTornActual(partida.getJugadors().get(i).getTorn());
                            gui.setAvatarJugadorActiu(gui.getTornActual().getJugadorTorn());
                            gui.actualitzaBotons();
                            if (!gui.getTornActual().getJugadorTorn().isHaFetFold() && !gui.getTornActual().getJugadorTorn().isAllin() && !partida.getJugadors().get(i).isEliminat()) {
                                if (gui.getTornActual().getJugadorTorn() instanceof Bot) {
                                    Bot bot = (Bot) gui.getTornActual().getJugadorTorn();
                                    gui.getTornActual().resume();
                                    Thread.sleep(2500);
                                    bot.jugadaBot(controlIA, fase, countFase);
                                    gui.getTornActual().run();
                                } else {
                                    gui.getTornActual().run();
                                }
                                gui.setAvatarJugadorInActiu(gui.getTornActual().getJugadorTorn());
                                gui.gestionarFitxes(partida.getJugadors());
                            }
                        }
                    }
                }
            } else {
                System.out.println("HOLA error events fase");
            }



            //Mira SI algun jugador (que no a fet fold) a apostat diferent
            boolean hanApostatDiferent = false;
            for (int i = 0; i < partida.getJugadors().size(); i++) {
                if (!partida.getJugadors().get(i).isHaFetFold() && !partida.getJugadors().get(i).isAllin() && !partida.getJugadors().get(i).isEliminat()) {
                    System.out.println("aposta minima: " + fase.getApostaMinima() + ", aposta jugador: " + partida.getJugadors().get(i).getAposta().getQuantitat() + " " + partida.getJugadors().get(i).getAlias());
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
            if (partida.getJugadors().get(i).getMaActual().getCombinacio() == combinacio
                    && partida.getJugadors().get(i).getMaActual().getValorMesAlt() == valorMesAlt
                    && partida.getJugadors().get(i).getMaActual().getValorDesempat() == valorDesempat) {
                posiblesGuanyadors.add(partida.getJugadors().get(i));
            }
        }
        return posiblesGuanyadors;
    }

    public void determinarJugadorsEliminats() {
        System.out.println("ELIMINANT JUGADORS...");
        System.out.println("Hi han " + partida.getJugadors().size() + " jugadors");
        for (Jugador j : partida.getJugadors()) {
            System.out.print("comprovant el jugador " + j.getAlias() + " amb " + j.getFitxesActuals());
            if (j.getFitxesActuals() <= 0) {
                System.out.println(" ELIMINAR!!!");
                j.setEliminat(true);
                gui.gestionarJugadorEliminat(j);
            } else {
                System.out.println(" SALVAR!!!");
            }
        }
        System.out.println("");
    }
}
