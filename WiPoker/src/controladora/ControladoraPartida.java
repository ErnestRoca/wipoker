/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Aposta;
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

    private Taula taula;
    private Baralla baralla;
    private Partida partida;
    private ArrayList<Jugador> jugadors;

    private ControladoraCartes controlCartes = new ControladoraCartes();
    private ControladoraIA controlIA = new ControladoraIA();
    private ControladoraJoc controlJoc = new ControladoraJoc();

    public ControladoraPartida(byte maxJugadors) {
        super();
        baralla = controlJoc.crearBaralla();
        taula = new Taula(maxJugadors, baralla);
        partida = new Partida(Calendar.getInstance());
        taula.setPartidaActual(partida);
        jugadors = partida.getJugadors();
    }

    public boolean taulaIsFull() {
        boolean completa = taula.getPlaces() - taula.getCadiresOcupades() == 0;
        return completa;
    }

    public void afegirJugador(Jugador nouJugador) {
        if (!taulaIsFull()) {
            jugadors.add(nouJugador);
            taula.setCadiresOcupades((byte) (taula.getCadiresOcupades() + 1));
        } else {
            //implementar metodo no quedanPlazas
        }
    }

    public void iniciarRonda() {
        Ronda novaRonda = new Ronda(0);
        novaRonda.setPartida(partida);
        partida.getRondes().add(novaRonda);
        controlJoc.barallar(baralla);
        for (int i = 0; i < 4; i++) {
            gestionarFase(novaRonda);
        }
        novaRonda.setJugadorGuanyadorRonda(determinarGuanyador());
        novaRonda.getJugadorGuanyadorRonda().setFitxesActuals(novaRonda.getPot());
        Fase.setNumFase((byte) 0);
        determinarJugadorsEliminats();
        novaRonda.getFases().clear();
        partida.getRondes().clear();
    }

    public void gestionarFase(Ronda ronda) {
        //Clase fase te dos static: array string nom fases i byte amb el numero de fase
        //Passem al constructor l'string de l'index de la fase
        Fase novaFase = new Fase(Fase.getNomFases()[Fase.getNumFase()]);
        ronda.getFases().add(novaFase);
        novaFase.setRonda(ronda);
        if (Fase.getNumFase() == 1) {
            repartirCartesPrivades();
            determinarCombinacioPreFlop();
            for (Jugador j : jugadors) {
                apostar(j, 0, ronda);
            }
        } else if (Fase.getNumFase() == 2) {
            cremarCartes();
            aixecarCartes((byte) 3);
            determinarCombinacio();
            for (Jugador j : jugadors) {
                apostar(j, 0, ronda);
            }
        } else if (Fase.getNumFase() == 3) {
            cremarCartes();
            aixecarCartes((byte) 1);
            determinarCombinacio();
            for (Jugador j : jugadors) {
                apostar(j, 0, ronda);
            }
        } else if (Fase.getNumFase() == 4) {
            cremarCartes();
            aixecarCartes((byte) 1);
            determinarCombinacio();
            for (Jugador j : jugadors) {
                apostar(j, 0, ronda);
            }
        }

        //Al finalitzar la fase afegir potFase al pot de la ronda
    }

    public void apostar(Jugador jugador, int quantitat, Ronda ronda) {
        //modificar cuando este hecha gui
        int fase = ronda.getFases().size();
        if (quantitat > ronda.getFases().get(fase).getApostes().get(ronda.getFases().size()).getQuantitat()) {
            jugador.setAposta(new Aposta(jugador, quantitat));
            jugador.setFitxesActuals(jugador.getFitxesActuals() - quantitat);
            ronda.setPot(ronda.getPot() + quantitat);
        }
    }

    private void determinarCombinacioPreFlop(ArrayList<Jugador> jugadors) {
        for (Jugador j : jugadors) {
            ArrayList<Carta> cartes = j.getMaActual().getCartes();
            byte combinacio = (byte) (cartes.get(0).equals(cartes.get(1)) ? 1 : 0);
            j.getMaActual().setCombinacio(combinacio);
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

    public Jugador desempat() {
        Jugador jugadorAux = jugadors.get(0);
        byte puntuacioMesAlta = cartaMesAlta(jugadorAux.getMaActual().getCartes());
        for (Jugador jugador : jugadors) {
            byte puntuacio = cartaMesAlta(jugador.getMaActual().getCartes());
            if (puntuacio > puntuacioMesAlta) {
                puntuacioMesAlta = puntuacio;
                jugadorAux =
                        jugador;
            }

        }
        return jugadorAux;
    }

    private Jugador determinarGuanyador() {
        ArrayList<Jugador> jugadorsOrdenats = jugadors;
        Comparator c = new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Jugador j1 = (Jugador) o1;
                Jugador j2 = (Jugador) o2;
                return j1.getMaActual().getCombinacio() - j2.getMaActual().getCombinacio();
            }
        };
        Collections.sort(jugadorsOrdenats, c);
        //arreglar para retorno misma combinacion
        return jugadorsOrdenats.get(0);
    }

    private void determinarJugadorsEliminats() {
        for (Jugador j : jugadors) {
            if (j.getFitxesActuals() <= 0) {
                jugadors.remove(j);
            }

        }
    }
}
