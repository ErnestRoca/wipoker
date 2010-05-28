/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Bot;
import domini.Fase;
import domini.Partida;
import domini.Ronda;

/**
 *
 * @author wida45787385
 */
public class ControladoraIA {

    public Partida partida = null;
    public ControladoraCartes cc;

    public ControladoraIA(Partida partida, ControladoraCartes cc) {
        this.partida = partida;
        this.cc = cc;
    }

    public double calcularAposta(Bot jugador) {
        double aposta = 0.0;
        double valorMa = jugador.getMaActual().getCombinacio();
        double valorCarta = jugador.getMaActual().getValorMesAlt();
        Ronda ultimaRonda = partida.getRondes().get(partida.getRondes().size());
        Fase ultimafase = ultimaRonda.getFases().get(ultimaRonda.getFases().size());
        double apostaMinima = ultimafase.getApostaMinima();

        //PRE-FLOP
        if (Fase.getNumFase() == 1) {
            //Pre-flop--->Parella
            if (valorMa == 2) {
                if (valorCarta >= 10) {
                    //fer RISE totes les fitxes actuals
                } else if (valorCarta >= 7) {
                    //fer RISE 3 o 4 vegades les fitxes actuals
                } else {
                    //fer RISE doblar l'aposta minima
                }
            //Pre-flop--->Projecte escala color
            } else if (cc.projecteColor(jugador) && cc.projecteEscala(jugador)) {
                //fer RISE doblar o triplicar l'aposta minima
            //Pre-flop-->Projecte color
            } else if (cc.projecteColor(jugador)) {
                //fer RISE doblar l'aposta minima
            //Pre-flop--->Projecte escala
            } else if (cc.projecteEscala(jugador)) {
                //fer RISE foblar l'aposta minima
            //Pre-flop--->Carta alta
            } else {
                if (valorCarta > 10) {
                    //fer RISE doblar l'aposta minima
                } else if (valorCarta > 7) {
                    //fer CALL
                } else {
                    //fer FOLD
                }
            }
        //FLOP
        } else if (Fase.getNumFase() == 2) {
            //Flop-->Combinacio mes alta que parella
            if (valorMa >= 3) {
                //fer RISE totes les fitxes actuals
            //Flop-->Parella
            } else if (valorMa == 2) {
                if (valorCarta > 10) {
                    //fer RISE doblar aposta minima
                } else {
                    //fer CALL
                }
            //Flop-->Carta alta
            } else {
                //fer FOLD
            }
        //TURN
        } else if (Fase.getNumFase() == 3) {
            //TURN-->Combinacio mes alta que doble parella
            if (valorMa >= 4) {
                //fer RISE totes les fitxes actuals
            //TURN-->Doble Parella
            } else if (valorMa == 3) {
                if (valorCarta > 10) {
                    //fer RISE triplicar aposta minima
                } else {
                    //fer RISE doblar aposta minima
                }
            //TURN--->Parella
            } else if (valorMa == 2) {
                if (valorCarta >= 11) {
                    //fer CALL
                } else {
                    //fer FOLD
                }
            //TURN-->Carta alta
            } else {
                //fer FOLD
            }
        //RIVER
        } else if (Fase.getNumFase() == 4) {
            //RIVER-->Combinacio mes alta que doble parella
            if (valorMa >= 4) {
                //fer RISE totes les fitxes actuals
            //RIVER-->Doble Parella
            } else if (valorMa == 3) {
                if (valorCarta >= 11) {
                    //fer RISE triplicar aposta minima
                } else {
                    //fer CALL
                }
            //RIVER--->Parella
            } else if (valorMa == 2) {
                if (valorCarta >= 12) {
                    //fer CALL
                } else {
                    //fer FOLD
                }
            //RIVER-->Carta alta
            } else {
                //fer FOLD
            }
        }
        return aposta;
    }
}