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
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author wida45787385
 */
public class ControladoraJoc {

    public ControladoraJoc() {
    }

    public Baralla crearBaralla() {
        ArrayList<Carta> cartes = new ArrayList<Carta>();
        for (byte i = 2; i <= 14; i++) {
            cartes.add(new Carta(0, i));
        }
        for (byte i = 2; i <= 14; i++) {
            cartes.add(new Carta(1, i));
        }
        for (byte i = 2; i <= 14; i++) {
            cartes.add(new Carta(2, i));
        }
        for (byte i = 2; i <= 14; i++) {
            cartes.add(new Carta(3, i));
        }
        Baralla b = new Baralla();
        b.setCartes(cartes);
        return b;
    }

    public ArrayList<Carta> repartirCartesPrivades(ArrayList<Jugador> jugadors, Baralla baralla) {
        ArrayList<Carta> privades = new ArrayList<Carta>();
        for (int i = 0; i <= 1; i++) {

            for (Jugador j : jugadors) {
                if (!j.isEliminat()) {
                    privades.add(baralla.getCartes().get(baralla.getCartesActuals() - 1));

                    j.getMaActual().getCartes().add(privades.get(privades.size() - 1));

                    baralla.setCartesActuals((baralla.getCartesActuals() - 1));
                }

            }
        }
        return privades;
    }

    public ArrayList<Carta> aixecarCartes(ArrayList<Jugador> jugadors, Baralla baralla, int numCartes) {
        ArrayList<Carta> publiques = new ArrayList<Carta>();
        for (int i = 0; i < numCartes; i++) {

            publiques.add(baralla.getCartes().get(baralla.getCartesActuals() - 1));
            for (Jugador j : jugadors) {
                if (j.isHaFetFold() == false && !j.isEliminat()) {
                    j.getMaActual().getCartes().add(baralla.getCartes().get(baralla.getCartesActuals() - 1));
                }
            }
            baralla.setCartesActuals((baralla.getCartesActuals() - 1));

        }

        return publiques;
    }

    public void cremarCartes(Baralla baralla) {
        //baralla.getCartes().remove(baralla.getCartes().size() - 1);
        baralla.setCartesActuals((baralla.getCartesActuals() - 1));
    }

    public void barallar(Baralla baralla) {
        Collections.shuffle(baralla.getCartes());
    }

    public void apostar(Jugador jugador, int quantitat, Fase fase) {
        double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        jugador.setFitxesActuals((int) ((int) (jugador.getFitxesActuals() - quantitat) + quantitatAnterior));
        jugador.setApostaTotalRonda((int) ((jugador.getApostaTotalRonda() + quantitat) - quantitatAnterior));
        Aposta aposta = new Aposta(jugador, quantitat);
        jugador.setAposta(aposta);
        fase.getApostes().add(aposta);
        fase.getRonda().setPot((fase.getRonda().getPot()) + quantitat);
    }

    public synchronized void ferFold(Jugador jugador, Fase fase) {
        jugador.setHaFetFold(true);
        System.out.println(jugador.getAlias() + " fa FOLD");
    }

    public synchronized void ferCall(Jugador jugador, Fase fase, int apostaMinima) {
        if (jugador.getFitxesActuals() < apostaMinima && jugador.getFitxesActuals() >= 0) {
            jugador.setHaFetAllin(true);
            apostar(jugador, jugador.getFitxesActuals(), fase);
            System.out.println(jugador.getAlias() + "Fa Allin");
        } else {
            int aposta = (int) (apostaMinima);
            apostar(jugador, aposta, fase);
            fase.setApostaMinima(apostaMinima);
            System.out.println(jugador.getAlias() + " fa CALL");
        }
    }

    public synchronized void ferCheck(Jugador jugador, Fase fase, int apostaMinima) {
        //no fa res. Ho hem fet volent
        fase.setApostaMinima(apostaMinima);
        System.out.println(jugador.getAlias() + " fa CHECK");
    }

    public synchronized void ferBlind(Jugador jugador, Fase fase, int dinersApostats) {
        if (jugador.getFitxesActuals() < dinersApostats && jugador.getFitxesActuals() >= 0) {
            jugador.setHaFetAllin(true);
            apostar(jugador, jugador.getFitxesActuals(), fase);
            System.out.println(jugador.getAlias() + "Fa Allin");
        } else {
            double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
            jugador.setFitxesActuals((int) ((int) (jugador.getFitxesActuals() - dinersApostats) + quantitatAnterior));
            jugador.setApostaTotalRonda((int) ((jugador.getApostaTotalRonda() + dinersApostats) - quantitatAnterior));
            Aposta aposta = new Aposta(jugador, dinersApostats);
            jugador.setAposta(aposta);
            fase.getApostes().add(aposta);
            fase.getRonda().setPot((fase.getRonda().getPot()) + dinersApostats);
            fase.setApostaMinima(dinersApostats);
            System.out.println(jugador.getAlias() + " fa BLIND, puja: " + dinersApostats);
        }
    }

    public synchronized void ferRaise(Jugador jugador, Fase fase, int apostaMinima, int dinersApostats) {
        if (jugador.getFitxesActuals() < apostaMinima && jugador.getFitxesActuals() >= 0) {
            jugador.setHaFetAllin(true);
            apostar(jugador, jugador.getFitxesActuals(), fase);
            System.out.println(jugador.getAlias() + "Fa Allin");
        } else if (jugador.getFitxesActuals() < (apostaMinima + dinersApostats)) {
            jugador.setHaFetAllin(true);
            apostar(jugador, jugador.getFitxesActuals(), fase);
            System.out.println(jugador.getAlias() + "Fa Allin");
        } else {
            apostar(jugador, (apostaMinima + dinersApostats), fase);
            fase.setApostaMinima(apostaMinima + dinersApostats);
            System.out.println(jugador.getAlias() + " fa RAISE, puja: " + dinersApostats);
        }

    }

    public void repartirPremi(ArrayList<Jugador> jugadors, int pot) {
        for (int i = 0; i < jugadors.size(); i++) {
            System.out.println(jugadors.get(i).getAlias() + ", fitxes actuals: " + jugadors.get(i).getFitxesActuals());
        }
        if (jugadors.size() == 1) {
            jugadors.get(0).setFitxesActuals((jugadors.get(0).getFitxesActuals() + pot));
        } else if (jugadors.size() > 1) {
            //Pot2: po1 - fitxes apostades pels jugadors guanyadors
            int pot2 = pot;
            for (int i = 0; i < jugadors.size(); i++) {
                pot2 -= jugadors.get(i).getApostaTotalRonda();
            }
            //Percentatge Total apostat pels jugadors guanyadors respecte el pot1
            int percentatgeTotal = 0;
            for (int i = 0; i < jugadors.size(); i++) {
                percentatgeTotal += jugadors.get(i).getApostaTotalRonda() * pot / 100;
            }
            for (int i = 0; i < jugadors.size(); i++) {
                //Percentatge apostat pel jugador en el pot 1
                double percentatgeGuanys = jugadors.get(i).getApostaTotalRonda() * pot / 100;
                //Percentatge guanys pel jugador en el pot 2
                double percentatgeGuanys2 = 100 * percentatgeGuanys / percentatgeTotal;
                int premi = (int) ((pot2 * percentatgeGuanys2 / 100) + jugadors.get(i).getApostaTotalRonda());
                jugadors.get(i).setFitxesActuals(jugadors.get(i).getFitxesActuals() + premi);
                System.out.println("El " + jugadors.get(i).getAlias() + " guanya: " + premi);
            }
        }
    }
}
