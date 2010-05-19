/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * prova
 */
package controladora;

import domini.Baralla;
import domini.Fase;
import domini.Jugador;
import domini.Ma;
import domini.Partida;
import domini.Ronda;
import domini.Taula;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartida {

    private Taula taula;
    private Baralla baralla;
    public Partida partida;
    private ArrayList<Jugador> jugadors;
    public ControladoraCartes controlCartes = new ControladoraCartes();
    public ControladoraIA controlIA = new ControladoraIA();
    public ControladoraJoc controlJoc = new ControladoraJoc();

    public ControladoraPartida(byte maxJugadors) {
        //super();
        baralla = controlJoc.crearBaralla();
        taula = new Taula(maxJugadors, baralla);
        partida = new Partida(Calendar.getInstance());
        taula.setPartidaActual(partida);
        //jugadors = partida.getJugadors();
        jugadors = new ArrayList<Jugador>(maxJugadors);
    }

    public boolean taulaIsFull() {
        boolean completa = taula.getPlaces() - taula.getCadiresOcupades() == 0;
        return completa;
    }

    public void afegirJugador(Jugador nouJugador) {

        if (!taulaIsFull()) {
            nouJugador.setMaActual(new Ma());
            jugadors.add(nouJugador);
            taula.setCadiresOcupades((byte) (taula.getCadiresOcupades() + 1));
        } else {
            System.out.println(taula.getCadiresOcupades());
        }
    }

    public void jugar() {
        while (jugadors.size() > 1) {
            int boto = 0;
            iniciarRonda(boto);
            boto++;
        }
    }

    public void iniciarRonda(int boto) {
        Ronda novaRonda = new Ronda(0);
        novaRonda.setPartida(partida);
        partida.getRondes().add(novaRonda);
        controlJoc.barallar(baralla);
        for (int i = 0; i < 4; i++) {
            gestionarFase(novaRonda, boto);
        }
        determinarCombinacio();
        ArrayList<Jugador> jugadorsGuanyadors = determinarGuanyador();
        controlJoc.repartirPremi(jugadorsGuanyadors, novaRonda.getPot());
        novaRonda.setJugadorGuanyadorRonda((jugadorsGuanyadors));
        Fase.setNumFase((byte) 0);
        determinarJugadorsEliminats();
        novaRonda.getFases().clear();
    }

    public void gestionarFase(Ronda ronda, int boto) {
        //Clase fase te dos static: array string nom fases i byte amb el numero de fase
        //Passem al constructor l'string de l'index de la fase
        Fase novaFase = new Fase(Fase.getNomFases()[Fase.getNumFase()]);
        ronda.getFases().add(novaFase);
        novaFase.setRonda(ronda);
        if (Fase.getNumFase() == 1) {
            eventsPreFlop(0, ronda, boto);
        } else if (Fase.getNumFase() == 2) {
            eventsFlop(ronda, boto);
        } else if (Fase.getNumFase() == 3) {
            eventsTurn(ronda, boto);
        } else if (Fase.getNumFase() == 4) {
            eventsRiver(ronda, boto);
        }

        //Al finalitzar la fase afegir potFase al pot de la ronda
    }

    private void eventsPreFlop(int quanitat, Ronda ronda, int boto) {
        //cega petita i gran
        controlJoc.apostar(jugadors.get(boto + 1), quanitat, ronda);        //Cega Petita
        controlJoc.apostar(jugadors.get(boto + 2), (quanitat * 2), ronda);  //Cega Gran
        controlJoc.repartirCartesPrivades(jugadors, baralla);

        for (int i = boto + 3; i < jugadors.size(); i++) {
            controlJoc.apostar(jugadors.get(i), 0, ronda);
        }

        for (int i = 0; i < boto + 3; i++) {
            controlJoc.apostar(jugadors.get(i), 0, ronda);
        }
    }

    private void eventsFlop(Ronda ronda, int boto) {
        controlJoc.cremarCartes(baralla);
        controlJoc.aixecarCartes(jugadors, baralla, (byte) 3);
        for (int i = boto + 3; i < jugadors.size(); i++) {
            controlJoc.apostar(jugadors.get(i), 0, ronda);
        }

        for (int i = 0; i < boto + 3; i++) {
            controlJoc.apostar(jugadors.get(i), 0, ronda);
        }
    }

    private void eventsTurn(Ronda ronda, int boto) {
        controlJoc.cremarCartes(baralla);
        controlJoc.aixecarCartes(jugadors, baralla, (byte) 1);
        for (int i = boto + 3; i < jugadors.size(); i++) {
            controlJoc.apostar(jugadors.get(i), 0, ronda);
        }

        for (int i = 0; i < boto + 3; i++) {
            controlJoc.apostar(jugadors.get(i), 0, ronda);
        }
    }

    private void eventsRiver(Ronda ronda, int boto) {
        controlJoc.cremarCartes(baralla);
        controlJoc.aixecarCartes(jugadors, baralla, (byte) 1);
        for (int i = boto + 3; i < jugadors.size(); i++) {
            controlJoc.apostar(jugadors.get(i), 0, ronda);
        }

        for (int i = 0; i < boto + 3; i++) {
            controlJoc.apostar(jugadors.get(i), 0, ronda);
        }

    }

    private void determinarCombinacioPreFlop(ArrayList<Jugador> jugadors) {
        for (Jugador j : jugadors) {
            if (controlCartes.esParella(j)) {
            } else if (controlCartes.valorMesAlt(j));
        }
    }

    public void determinarCombinacio() {
        for (Jugador j : jugadors) {
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

    private ArrayList<Jugador> determinarGuanyador() {

        ArrayList<Jugador> posiblesGuanyadors = new ArrayList<Jugador>();
        int comb = 0;
        for (int i = 0; i < jugadors.size(); i++) {
            if (jugadors.get(i).getMaActual().getCombinacio() > comb) {
                comb = jugadors.get(i).getMaActual().getCombinacio();
            }
        }
        for (Jugador j : jugadors) {
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
                if (j.getMaActual().getValorMesAlt() != comb) {
                    jug.add(j);
                }
            }
            posiblesGuanyadors.removeAll(jug);
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
        for (Jugador j : jugadors) {
            if (j.getFitxesActuals() <= 0) {
                jug.add(j);
            }
        }
        jugadors.removeAll(jug);
    }
}
