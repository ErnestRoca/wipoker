/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Baralla;
import domini.Fase;
import domini.Jugador;
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

    public Taula taula;
    public Baralla baralla;
    public Partida partida;
    public ControladoraCartes controlCartes = new ControladoraCartes();
    public ControladoraIA controlIA = new ControladoraIA();
    public ControladoraJoc controlJoc = new ControladoraJoc();
    public ControladoraGui gui;

    public ControladoraPartida(int maxJugadors, ControladoraGui gui) {
        //super();
        this.gui = gui;
        baralla = controlJoc.crearBaralla();
        taula = new Taula(maxJugadors, baralla);
        partida = new Partida(Calendar.getInstance());
        taula.setPartidaActual(partida);
        //jugadors = partida.getJugadors();
        partida.setJugadors(new ArrayList<Jugador>(maxJugadors));
    }

    public void jugar() throws InterruptedException {
        while (partida.getJugadors().size() > 1) {
            int boto = 0;
            iniciarRonda(boto);
            boto++;
        }
    }

    public void iniciarRonda(int boto) throws InterruptedException {
        Ronda novaRonda = new Ronda(0);
        novaRonda.setPartida(partida);
        partida.getRondes().add(novaRonda);
        baralla = controlJoc.crearBaralla();
        controlJoc.barallar(baralla);

        for (int i = 0; i < 4; i++) {
            Fase novaFase = new Fase(Fase.getNomFases()[Fase.getNumFase()], novaRonda, 20);
            this.gui.setFaseActual(novaFase);
            novaRonda.getFases().add(novaFase);
            gestionarFase(novaFase, boto);

        }
        determinarCombinacio();
        ArrayList<Jugador> jugadorsGuanyadors = determinarGuanyador();
        controlJoc.repartirPremi(jugadorsGuanyadors, novaRonda.getPot());
        novaRonda.setJugadorGuanyadorRonda((jugadorsGuanyadors));
        Fase.setNumFase(0);
        determinarJugadorsEliminats();
        novaRonda.getFases().clear();
        for (Jugador j : partida.getJugadors()) {
            System.out.println("\n\n" + j);
            System.out.println(j.getMaActual().getCartes());
            System.out.println("comb:" + j.getMaActual().getCombinacio());
        }
        for (Jugador jugador : partida.getJugadors()) {
            jugador.getMaActual().getCartes().clear();
        }

    }

    public void gestionarFase(Fase novaFase, int boto) throws InterruptedException {
        //Clase fase te dos static: array string nom fases i byte amb el numero de fase
        //Passem al constructor l'string de l'index de la fase
        if (Fase.getNumFase() == 1) {
            eventsPreFlop(novaFase.getApostaMinima(), novaFase, boto);
        } else if (Fase.getNumFase() == 2) {
            eventsFlop(novaFase.getApostaMinima(), novaFase, boto);
        } else if (Fase.getNumFase() == 3) {
            eventsTurn(novaFase.getApostaMinima(), novaFase, boto);
        } else if (Fase.getNumFase() == 4) {
            eventsRiver(novaFase.getApostaMinima(), novaFase, boto);
        }

        //Al finalitzar la fase afegir potFase al pot de la ronda
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
