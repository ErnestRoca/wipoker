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
    public ControladoraCartes controlCartes = new ControladoraCartes();
    public ControladoraIA controlIA = new ControladoraIA();
    public ControladoraJoc controlJoc = new ControladoraJoc();
    private ControladoraGui gui;

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

    public boolean taulaIsFull() {
        boolean completa = taula.getPlaces() - taula.getCadiresOcupades() == 0;
        return completa;
    }

    public void afegirJugador(Jugador nouJugador) {

        if (!taulaIsFull()) {
            nouJugador.setMaActual(new Ma());
            partida.getJugadors().add(nouJugador);
            taula.setCadiresOcupades((taula.getCadiresOcupades() + 1));
        } else {
            System.out.println(taula.getCadiresOcupades());
        }
    }

    public void jugar() throws InterruptedException {
        while (partida.getJugadors().size() > 2) {
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
            System.out.println("\n\n"+j);
            System.out.println(j.getMaActual().getCartes());
            System.out.println("comb:"+j.getMaActual().getCombinacio());
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
         for (Jugador j: partida.getJugadors()) {
             j.setTorn(new Torn(j));
         }
         gui.setTornActual(partida.getJugadors().get(0).getTorn());
         boolean fi = false;
         while(!fi){
             if (numJugadorsTornFinalitzat == partida.getJugadors().size()) {
                 fi = true;
             } else {
                 for (Jugador j: partida.getJugadors()) {
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
