/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * prova
 */
package controladora;

import domini.Fase;
import domini.Jugador;
import domini.Ma;
import domini.Ronda;
import java.util.ArrayList;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartidaOnline extends ControladoraPartida {

    public ControladoraPartidaOnline(int maxJugadors, ControladoraGui gui) {
        super(maxJugadors, gui);
    }

    public boolean taulaIsFull() {
        boolean completa = super.taula.getPlaces() - super.taula.getCadiresOcupades() == 0;
        return completa;
    }

    public void afegirJugador(Jugador nouJugador) {
        if (!taulaIsFull()) {
            nouJugador.setMaActual(new Ma());
            super.partida.getJugadors().add(nouJugador);
            super.taula.setCadiresOcupades((super.taula.getCadiresOcupades() + 1));
        }
    }


    public void jugarLocal() throws InterruptedException {
        int boto = 0;
        iniciarRonda(boto);
        boto++;
    }





    private void eventsPreFlop(int apostaMin, Fase fase, int boto) throws InterruptedException {
        //cega petita i gran
        controlJoc.apostar(partida.getJugadors().get(boto + 1), (apostaMin / 2), fase);        //Cega Petita
        controlJoc.apostar(partida.getJugadors().get(boto + 2), apostaMin, fase);  //Cega Gran
        controlJoc.repartirCartesPrivades(partida.getJugadors(), baralla);
        /*
        for (int i = boto + 3; i < partida.getJugadors().size(); i++) {
        controlJoc.apostar(partida.getJugadors().get(i), 100, fase);
        }

        for (int i = 0; i < boto + 3; i++) {
        controlJoc.apostar(partida.getJugadors().get(i), 100, fase);
        }
         * */
        int minima = apostaMin;
        int numJugadorsTornFinalitzat = 0;
        for (Jugador j : partida.getJugadors()) {
            System.out.println(j);
            j.setTorn(new Torn(j));
        }
        gui.setTornActual(partida.getJugadors().get(0).getTorn());

        boolean fi = false;
        while (!fi) {
            if (numJugadorsTornFinalitzat == partida.getJugadors().size()) {
                fi = true;
            } else {
                for (Jugador j : partida.getJugadors()) {
                    gui.setTornActual(j.getTorn());
                    gui.setFaseActual(fase);
                    if (j.isHaFetFold()) {
                        numJugadorsTornFinalitzat++;
                    } else if (j.getAposta().getQuantitat() >= minima) {
                        numJugadorsTornFinalitzat++;
                        if (j.getAposta().getQuantitat() > minima) {
                            numJugadorsTornFinalitzat = 0;
                            minima = (int) j.getAposta().getQuantitat();
                        }
                    }
                }
            }
        }
    }

    private void eventsFlop(int apostaMin, Fase fase, int boto) {
        controlJoc.cremarCartes(baralla);
        controlJoc.aixecarCartes(partida.getJugadors(), baralla, 3);
        for (int i = boto + 3; i < partida.getJugadors().size(); i++) {
            controlJoc.apostar(partida.getJugadors().get(i), 100, fase);
        }

        for (int i = 0; i < boto + 3; i++) {
            controlJoc.apostar(partida.getJugadors().get(i), 100, fase);
        }
    }

    private void eventsTurn(int apostaMin, Fase fase, int boto) {
        controlJoc.cremarCartes(baralla);
        controlJoc.aixecarCartes(partida.getJugadors(), baralla, 1);
        for (int i = boto + 3; i < partida.getJugadors().size(); i++) {
            controlJoc.apostar(partida.getJugadors().get(i), 100, fase);
        }

        for (int i = 0; i < boto + 3; i++) {
            controlJoc.apostar(partida.getJugadors().get(i), 100, fase);
        }
    }

    private void eventsRiver(int apostaMin, Fase fase, int boto) {
        controlJoc.cremarCartes(baralla);
        controlJoc.aixecarCartes(partida.getJugadors(), baralla, 1);
        for (int i = boto + 3; i < partida.getJugadors().size(); i++) {
            controlJoc.apostar(partida.getJugadors().get(i), 100, fase);
        }

        for (int i = 0; i < boto + 3; i++) {
            controlJoc.apostar(partida.getJugadors().get(i), 100, fase);
        }

    }

    private void determinarCombinacioPreFlop(ArrayList<Jugador> jugadors) {
        for (Jugador j : jugadors) {
            if (controlCartes.esParella(j)) {
            } else if (controlCartes.valorMesAlt(j));
        }
    }

   

    private ArrayList<Jugador> determinarGuanyador() {

        ArrayList<Jugador> posiblesGuanyadors = new ArrayList<Jugador>();
        int comb = 0;
        for (int i = 0; i < partida.getJugadors().size(); i++) {
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
            for (int i = 0; i < posiblesGuanyadors.size(); i++) {
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
                for (int i = 0; i < posiblesGuanyadors.size(); i++) {
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

    private void determinarJugadorsEliminats() {
        ArrayList<Jugador> jug = new ArrayList<Jugador>();
        for (Jugador j : partida.getJugadors()) {
            if (j.getFitxesActuals() <= 0) {
                jug.add(j);
            }
        }
        partida.getJugadors().removeAll(jug);
    }
}
