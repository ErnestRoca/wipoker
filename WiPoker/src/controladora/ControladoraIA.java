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

    Partida partida = new Partida(Calendar.getInstance());

    public ControladoraIA() {

    }

    public double calcularAposta(Bot jugador) {
        double aposta = 0.0;
        double valorMa = jugador.getMaActual().getCombinacio();
        double valorCarta = jugador.getMaActual().getValorMesAlt();
        Ronda ultimaRonda = partida.getRondes().get(partida.getRondes().size());
        Fase ultimafase = ultimaRonda.getFases().get(ultimaRonda.getFases().size());
        double apostaMinima = ultimafase.getApostaMinima();
        if (valorMa == 10) {
            //escala reial
            aposta += jugador.getFitxesActuals();
        } else if (valorMa == 9) {
            //escala color
            aposta += jugador.getFitxesActuals();
        } else if (valorMa == 8) {
            //poker
            aposta += jugador.getFitxesActuals();
        } else if (valorMa == 7) {
            //full
            aposta += jugador.getFitxesActuals();
        } else if (valorMa == 6) {
            //color
            aposta += jugador.getFitxesActuals();
        } else if (valorMa == 5) {
            //escala
            aposta += jugador.getFitxesActuals();
        } else if (valorMa == 4) {
            //trio
            if (valorCarta == 13) {
                //trio asos
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 12) {
                //trio K
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 11) {
                //trio Q
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 10) {
                //trio J
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 9) {
                //trio 10
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 8) {
                //trio 9
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 7) {
                //trio 8
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 6) {
                //trio 7
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 5) {
                //trio 6
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 4) {
                //trio 5
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 3) {
                //trio 4
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 2) {
                //trio 3
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 1) {
                //trio 2
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            }

        } else if (valorMa == 3) {
            //doble parella
            if (valorCarta == 13) {
                //Parella asos
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 12) {
                //parella K
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 11) {
                //Parella Q
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 10) {
                //Parella J
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 9) {
                //Parella 10
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 2;
            } else if (valorCarta == 8) {
                //Parella 9
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 3;
            } else if (valorCarta == 7) {
                //Parella 8
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 4;
            } else if (valorCarta == 6) {
                //Parella 7
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 5;
            } else if (valorCarta == 5) {
                //Parella 6
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 5;
            } else if (valorCarta == 4) {
                //Parella 5
                aposta += apostaMinima;
            } else if (valorCarta == 3) {
                //Parella 4
                aposta += apostaMinima;
            } else if (valorCarta == 2) {
                //Parella 3
                aposta += apostaMinima;
            } else if (valorCarta == 1) {
                //Parella 2
                aposta += apostaMinima;
            }

        } else if (valorMa == 2) {
            //parella
            if (valorCarta == 13) {
                //Parella asos
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 12) {
                //parella K
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals();
            } else if (valorCarta == 11) {
                //Parella Q
                aposta += apostaMinima;
                aposta +=            jugador.getFitxesActuals();
            } else if (valorCarta == 10) {
                //Parella J
                aposta += apostaMinima;
                aposta += jugador.getFitxesActuals();
            } else if (valorCarta == 9) {
                //Parella 10
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 2;
            } else if (valorCarta == 8) {
                //Parella 9
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 3;
            } else if (valorCarta == 7) {
                //Parella 8
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 4;
            } else if (valorCarta == 6) {
                //Parella 7
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 5;
            } else if (valorCarta == 5) {
                //Parella 6
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 5;
            } else if (valorCarta == 4) {
                //Parella 5
                aposta += apostaMinima;
            } else if (valorCarta == 3) {
                //Parella 4
                aposta += apostaMinima;
            } else if (valorCarta == 2) {
                //Parella 3
                aposta += apostaMinima;
            } else if (valorCarta == 1) {
                //Parella 2
                aposta += apostaMinima;
            }

        } else if (valorMa == 1) {
            if (valorCarta == 13) {
                //as
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 5;
            } else if (valorCarta == 12) {
                //K
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 6;
            } else if (valorCarta == 11) {
                //Q
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 7;
            } else if (valorCarta == 10) {
                //J
                aposta += apostaMinima;
                aposta +=
                        jugador.getFitxesActuals() / 8;
            } else if (valorCarta == 9) {
                //10
                aposta += apostaMinima;

            } else if (valorCarta == 8) {
                //9
                //No fa res
            } else if (valorCarta == 7) {
                //8
                //No fa res
            } else if (valorCarta == 6) {
                //7
                //No fa res
            } else if (valorCarta == 5) {
                //6
                //No fa res
            } else if (valorCarta == 4) {
                //5
                //No fa res
            } else if (valorCarta == 3) {
                //4
                //No fa res
            } else if (valorCarta == 2) {
                //3
                //No fa res
            } else if (valorCarta == 1) {
                //2
                //No fa res
            }

        }

        return aposta;
    }

}
