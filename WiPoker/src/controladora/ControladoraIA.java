/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Bot;
import domini.Fase;
import domini.Partida;
import domini.Ronda;
import java.util.Calendar;

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
                //fer RISE doblar o triplicar les fitxes actuals
            //Pre-flop-->Projecte color
            } else if (cc.projecteColor(jugador)) {
                //fer RISE doblar les fitxes actuals
            //Pre-flop--->Carta alta
            } else {
                if (valorCarta > 10) {
                    //fer RISE doblar l'aposta minima
                } else if (valorCarta > 7) {
                    //fer call
                }
            }
        }

        return aposta;
    }
}
