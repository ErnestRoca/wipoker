/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Baralla;
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
    public ControladoraIA controlIA = new ControladoraIA();
    public ControladoraJoc controlJoc = new ControladoraJoc();
    public ControladoraGui gui;

    public ControladoraPartida(int maxJugadors, ControladoraGui gui) {
        this.gui = gui;
        baralla = controlJoc.crearBaralla();
        taula = new Taula(maxJugadors, baralla);
        partida = new Partida(Calendar.getInstance());
        taula.setPartidaActual(partida);
        partida.setJugadors(new ArrayList<Jugador>(maxJugadors));
    }

    public void jugar() throws InterruptedException {
        gui.mostrarAvatars(partida.getJugadors());
        Collections.sort(partida.getJugadors(), new Comparator() {

            public int compare(Object o1, Object o2) {
                Jugador j1 = (Jugador) o1;
                Jugador j2 = (Jugador) o2;
                return j1.getPosicioTaula() - j2.getPosicioTaula();
            }
        });
        System.out.println(partida.getJugadors().get(0));
        while (partida.getJugadors().size() > 1) {
            int boto = 0;
            iniciarRonda(boto);
            boto++;
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
                j.getAposta().setQuantitat(0);
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
        determinarJugadorsEliminats();
        novaRonda.getFases().clear();

        for (Jugador jugador : partida.getJugadors()) {
            jugador.getMaActual().getCartes().clear();
            if (jugador.isHaFetFold()) {
                jugador.setHaFetFold(false);

            }
        }
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
            //cega petita i gran
            if (boto == 0) {
                controlJoc.apostar(partida.getJugadors().get(1), (apostaMin / 2), fase);        //Cega Petita
                controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);  //Cega Gran
            } else if (boto == 1) {
                controlJoc.apostar(partida.getJugadors().get(0), (apostaMin / 2), fase);        //Cega Petita
                controlJoc.apostar(partida.getJugadors().get(1), apostaMin, fase);  //Cega Gran
            }

        } else {
            if (boto == partida.getJugadors().size() - 1 || boto == partida.getJugadors().size() - 2) {
                if (boto == partida.getJugadors().size() - 1) {
                    controlJoc.apostar(partida.getJugadors().get(0), apostaMin / 2, fase);
                    controlJoc.apostar(partida.getJugadors().get(1), apostaMin, fase);
                } else if (boto == partida.getJugadors().size() - 2) {
                    controlJoc.apostar(partida.getJugadors().get(boto + 1), apostaMin / 2, fase);
                    controlJoc.apostar(partida.getJugadors().get(0), apostaMin, fase);
                }

            } else {
                controlJoc.apostar(partida.getJugadors().get(boto + 1), apostaMin / 2, fase);
                controlJoc.apostar(partida.getJugadors().get(boto + 2), apostaMin, fase);
            }

        }
        controlJoc.repartirCartesPrivades(partida.getJugadors(), baralla);

        int minima = apostaMin;
        int numJugadorsTornFinalitzat = 0;

        boolean fi = false;
        while (!fi) {
            if (numJugadorsTornFinalitzat >= partida.getJugadors().size() - 1) {
                fi = true;
            } else {
                for (int i = boto + 1; i <
                        partida.getJugadors().size(); i++) {
                    if (numJugadorsTornFinalitzat == partida.getJugadors().size() - 1) {
                        break;
                    }

                    gui.setTornActual(partida.getJugadors().get(i).getTorn());
                    if (!gui.getTornActual().getJugadorTorn().isHaFetFold()) {
                        gui.getTornActual().run();
                        if (partida.getJugadors().get(i).isHaFetFold()) {
                            numJugadorsTornFinalitzat++;
                        } else if (partida.getJugadors().get(i).getAposta().getQuantitat() == minima) {
                            numJugadorsTornFinalitzat++;
                        }

                        if (partida.getJugadors().get(i).getAposta().getQuantitat() != minima) {
                            numJugadorsTornFinalitzat = 0;
                            minima =
                                    (int) partida.getJugadors().get(i).getAposta().getQuantitat();
                        }

                    } else {
                        numJugadorsTornFinalitzat++;
                    }

                }
                for (int i = 0; i <=
                        boto; i++) {
                    if (numJugadorsTornFinalitzat == partida.getJugadors().size() - 1) {
                        break;
                    }

                    gui.setTornActual(partida.getJugadors().get(i).getTorn());
                    if (!gui.getTornActual().getJugadorTorn().isHaFetFold()) {
                        gui.getTornActual().run();
                        if (partida.getJugadors().get(i).isHaFetFold()) {
                            numJugadorsTornFinalitzat++;
                        } else if (partida.getJugadors().get(i).getAposta().getQuantitat() == minima) {
                            numJugadorsTornFinalitzat++;
                        }

                        if (partida.getJugadors().get(i).getAposta().getQuantitat() != minima) {
                            numJugadorsTornFinalitzat = 0;
                            minima =
                                    (int) partida.getJugadors().get(i).getAposta().getQuantitat();
                        }

                    } else {
                        numJugadorsTornFinalitzat++;
                    }

                }
            }
            if (!(partida.getJugadors().size() - numJugadorsTornFinalitzat == 1)) {
                fi = true;
            }

        }
    }

    public void eventsFlop(Fase fase, int boto) {
        controlJoc.cremarCartes(baralla);
        ArrayList<Carta> publiques = controlJoc.aixecarCartes(partida.getJugadors(), baralla, 3);
        gui.mostrarCartesComunitaries(publiques);
        int minima = 0;
        int numJugadorsTornFinalitzat = 0;
        for (Jugador j : partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }

        boolean fi = false;
        while (!fi) {
            if (numJugadorsTornFinalitzat >= partida.getJugadors().size() - 1) {
                fi = true;
            } else {
                for (int i = boto + 1; i <
                        partida.getJugadors().size(); i++) {
                    if (numJugadorsTornFinalitzat == partida.getJugadors().size() - 1) {
                        break;
                    }

                    gui.setTornActual(partida.getJugadors().get(i).getTorn());
                    if (!gui.getTornActual().getJugadorTorn().isHaFetFold()) {
                        gui.getTornActual().run();
                        if (partida.getJugadors().get(i).isHaFetFold()) {
                            numJugadorsTornFinalitzat++;
                        } else if (partida.getJugadors().get(i).getAposta().getQuantitat() == minima) {
                            numJugadorsTornFinalitzat++;
                        }

                        if (partida.getJugadors().get(i).getAposta().getQuantitat() != minima) {
                            numJugadorsTornFinalitzat = 0;
                            minima =
                                    (int) partida.getJugadors().get(i).getAposta().getQuantitat();
                        }

                    } else {
                        numJugadorsTornFinalitzat++;
                    }

                }
                for (int i = 0; i <=
                        boto; i++) {
                    if (numJugadorsTornFinalitzat == partida.getJugadors().size() - 1) {
                        break;
                    }

                    gui.setTornActual(partida.getJugadors().get(i).getTorn());
                    if (!gui.getTornActual().getJugadorTorn().isHaFetFold()) {
                        gui.getTornActual().run();
                        if (partida.getJugadors().get(i).isHaFetFold()) {
                            numJugadorsTornFinalitzat++;
                        } else if (partida.getJugadors().get(i).getAposta().getQuantitat() == minima) {
                            numJugadorsTornFinalitzat++;
                        }

                        if (partida.getJugadors().get(i).getAposta().getQuantitat() != minima) {
                            numJugadorsTornFinalitzat = 0;
                            minima =
                                    (int) partida.getJugadors().get(i).getAposta().getQuantitat();
                        }

                    } else {
                        numJugadorsTornFinalitzat++;
                    }

                }
            }
            if (!(partida.getJugadors().size() - numJugadorsTornFinalitzat == 1)) {
                fi = true;
            }

        }
    }

    public void eventsTurn(Fase fase, int boto) {
        controlJoc.cremarCartes(baralla);
        ArrayList<Carta> publiques = controlJoc.aixecarCartes(partida.getJugadors(), baralla, 1);
        gui.mostrarCartesComunitaries(publiques);
        int minima = 0;
        int numJugadorsTornFinalitzat = 0;
        for (Jugador j : partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }

        boolean fi = false;
        while (!fi) {
            if (numJugadorsTornFinalitzat >= partida.getJugadors().size() - 1) {
                fi = true;
            } else {
                for (int i = boto + 1; i <
                        partida.getJugadors().size(); i++) {
                    if (numJugadorsTornFinalitzat == partida.getJugadors().size() - 1) {
                        break;
                    }

                    gui.setTornActual(partida.getJugadors().get(i).getTorn());
                    if (!gui.getTornActual().getJugadorTorn().isHaFetFold()) {
                        gui.getTornActual().run();
                        if (partida.getJugadors().get(i).isHaFetFold()) {
                            numJugadorsTornFinalitzat++;
                        } else if (partida.getJugadors().get(i).getAposta().getQuantitat() == minima) {
                            numJugadorsTornFinalitzat++;
                        }

                        if (partida.getJugadors().get(i).getAposta().getQuantitat() != minima) {
                            numJugadorsTornFinalitzat = 0;
                            minima =
                                    (int) partida.getJugadors().get(i).getAposta().getQuantitat();
                        }

                    } else {
                        numJugadorsTornFinalitzat++;
                    }

                }
                for (int i = 0; i <=
                        boto; i++) {
                    if (numJugadorsTornFinalitzat == partida.getJugadors().size() - 1) {
                        break;
                    }

                    gui.setTornActual(partida.getJugadors().get(i).getTorn());
                    if (!gui.getTornActual().getJugadorTorn().isHaFetFold()) {
                        gui.getTornActual().run();
                        if (partida.getJugadors().get(i).isHaFetFold()) {
                            numJugadorsTornFinalitzat++;
                        } else if (partida.getJugadors().get(i).getAposta().getQuantitat() == minima) {
                            numJugadorsTornFinalitzat++;
                        }

                        if (partida.getJugadors().get(i).getAposta().getQuantitat() != minima) {
                            numJugadorsTornFinalitzat = 0;
                            minima =
                                    (int) partida.getJugadors().get(i).getAposta().getQuantitat();
                        }

                    } else {
                        numJugadorsTornFinalitzat++;
                    }

                }
            }
            if (!(partida.getJugadors().size() - numJugadorsTornFinalitzat == 1)) {
                fi = true;
            }

        }
    }

    public void eventsRiver(Fase fase, int boto) {
        controlJoc.cremarCartes(baralla);
        ArrayList<Carta> publiques = controlJoc.aixecarCartes(partida.getJugadors(), baralla, 1);
        gui.mostrarCartesComunitaries(publiques);

        int minima = 0;
        int numJugadorsTornFinalitzat = 0;
        for (Jugador j : partida.getJugadors()) {
            j.setTorn(new Torn(j));
        }

        boolean fi = false;
        while (!fi) {
            if (numJugadorsTornFinalitzat >= partida.getJugadors().size() - 1) {
                fi = true;
            } else {
                for (int i = boto + 1; i <
                        partida.getJugadors().size(); i++) {
                    if (numJugadorsTornFinalitzat == partida.getJugadors().size()) {
                        break;
                    }

                    gui.setTornActual(partida.getJugadors().get(i).getTorn());
                    if (!gui.getTornActual().getJugadorTorn().isHaFetFold()) {
                        gui.getTornActual().run();
                        if (partida.getJugadors().get(i).isHaFetFold()) {
                            numJugadorsTornFinalitzat++;
                        } else if (partida.getJugadors().get(i).getAposta().getQuantitat() == minima) {
                            numJugadorsTornFinalitzat++;
                        }

                        if (partida.getJugadors().get(i).getAposta().getQuantitat() != minima) {
                            numJugadorsTornFinalitzat = 0;
                            minima =
                                    (int) partida.getJugadors().get(i).getAposta().getQuantitat();
                        }

                    } else {
                        numJugadorsTornFinalitzat++;
                    }

                }
                for (int i = 0; i <=
                        boto; i++) {
                    if (numJugadorsTornFinalitzat == partida.getJugadors().size() - 1) {
                        break;
                    }

                    gui.setTornActual(partida.getJugadors().get(i).getTorn());
                    if (!gui.getTornActual().getJugadorTorn().isHaFetFold()) {
                        gui.getTornActual().run();
                        if (partida.getJugadors().get(i).isHaFetFold()) {
                            numJugadorsTornFinalitzat++;
                        } else if (partida.getJugadors().get(i).getAposta().getQuantitat() == minima) {
                            numJugadorsTornFinalitzat++;
                        }

                        if (partida.getJugadors().get(i).getAposta().getQuantitat() != minima) {
                            numJugadorsTornFinalitzat = 0;
                            minima =
                                    (int) partida.getJugadors().get(i).getAposta().getQuantitat();
                        }

                    } else {
                        numJugadorsTornFinalitzat++;
                    }

                }
            }
            if (!(partida.getJugadors().size() - numJugadorsTornFinalitzat == 1)) {
                fi = true;
            }

        }


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

    public ArrayList<Jugador> determinarGuanyador() {

        ArrayList<Jugador> posiblesGuanyadors = new ArrayList<Jugador>();
        int comb = 0;
        for (int i = 0; i <
                partida.getJugadors().size(); i++) {
            if (partida.getJugadors().get(i).getMaActual().getCombinacio() > comb) {
                comb = partida.getJugadors().get(i).getMaActual().getCombinacio();
            }

        }
        for (Jugador j : partida.getJugadors()) {
            if (j.getMaActual().getCombinacio() == comb) {
                posiblesGuanyadors.add(j);
            }

        }
        comb = 0;
        if (posiblesGuanyadors.size() > 1) {
            ArrayList<Jugador> jug = new ArrayList<Jugador>();
            for (int i = 0; i <
                    posiblesGuanyadors.size(); i++) {
                if (posiblesGuanyadors.get(i).getMaActual().getValorMesAlt() > comb) {
                    comb = posiblesGuanyadors.get(i).getMaActual().getValorMesAlt();
                }

            }
            for (Jugador j : posiblesGuanyadors) {
                if (j.getMaActual().getValorMesAlt() != comb) { //Calcula els jugadors perdedors
                    jug.add(j);
                }

            }
            posiblesGuanyadors.removeAll(jug);//Eliminem els jugadors perdedors.
            jug.clear();
            if (posiblesGuanyadors.size() > 1) {
                comb = 0;
                for (int i = 0; i <
                        posiblesGuanyadors.size(); i++) {
                    if (posiblesGuanyadors.get(i).getMaActual().getValorDesempat() > comb) {
                        comb = posiblesGuanyadors.get(i).getMaActual().getValorDesempat();
                    }

                }
                for (Jugador j : posiblesGuanyadors) {
                    if (j.getMaActual().getValorDesempat() != comb) {
                        jug.add(j);
                    }

                }

                posiblesGuanyadors.removeAll(jug);
            }

        }
        return posiblesGuanyadors;
    }

    public void determinarJugadorsEliminats() {
        ArrayList<Jugador> jug = new ArrayList<Jugador>();
        for (Jugador j : partida.getJugadors()) {
            if (j.getFitxesActuals() <= 0) {
                jug.add(j);
            }

        }
        partida.getJugadors().removeAll(jug);
    }
}
