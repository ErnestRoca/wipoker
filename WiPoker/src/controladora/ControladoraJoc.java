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

                privades.add(baralla.getCartes().get(baralla.getCartesActuals() - 1));

                j.getMaActual().getCartes().add(privades.get(privades.size() - 1));

                baralla.setCartesActuals((baralla.getCartesActuals() - 1));
            }
        }
        return privades;
    }

    public ArrayList<Carta> aixecarCartes(ArrayList<Jugador> jugadors, Baralla baralla, int numCartes) {
        ArrayList<Carta> publiques = new ArrayList<Carta>();
        for (int i = 0; i < numCartes; i++) {

            publiques.add(baralla.getCartes().get(baralla.getCartesActuals() - 1));
            for (Jugador j : jugadors) {
                if (j.isHaFetFold() == false) {
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
        //modificar cuando este hecha gui
        double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        jugador.setFitxesActuals((int) ((int) jugador.getFitxesActuals() - (quantitat - quantitatAnterior)));
        Aposta aposta = new Aposta(jugador, quantitat);
        jugador.setAposta(aposta);

        fase.getApostes().add(aposta);
        fase.getRonda().setPot(fase.getRonda().getPot() + quantitat);

    }

    public synchronized void ferFold(Jugador jugador, Fase fase) {
        jugador.setHaFetFold(true);
        //apostar(jugador, 0, fase);
        System.out.println(jugador.getAlias() + " fa FOLD");
    }

    public synchronized void ferCall(Jugador jugador, Fase fase, int apostaMinima) {
        if (jugador.getFitxesActuals() < apostaMinima) {
            jugador.setHaFetAllin(true);
            System.out.println("Fa Allin");
        }
//        } else {
        double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        boolean teDiners = jugador.getFitxesActuals() >= (apostaMinima - quantitatAnterior);
        // if (teDiners) {
        fase.setApostaMinima(apostaMinima);
        int aposta = (int) (apostaMinima);
        apostar(jugador, aposta, fase);
        System.out.println(jugador.getAlias() + " fa CALL");
//            } else {
//                System.out.println(jugador.getAlias() + " no pot aposstar CALL");
//            }
        //}
    }

    public synchronized void ferCheck(Jugador jugador, Fase fase, int apostaMinima) {
        //double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        //double quantitatAnterior = jugador.getAposta().getQuantitat();
        //if (quantitatAnterior == apostaMinima) {
        //no fa res. Ho hem fet volent
        fase.setApostaMinima(apostaMinima);
        //apostar(jugador, (int) (apostaMinima), fase);
        System.out.println(jugador.getAlias() + " fa CHECK");
        //}
    }

    public synchronized void ferBlind(Jugador jugador, Fase fase, int dinersApostats) {
        apostar(jugador, dinersApostats, fase);
        fase.setApostaMinima((int) (dinersApostats));
        System.out.println(jugador.getAlias() + " fa BLIND, puja: " + dinersApostats);
    }

    public synchronized void ferRaise(Jugador jugador, Fase fase, int apostaMinima, int dinersApostats) {
        if (jugador.getFitxesActuals() < apostaMinima) {
            jugador.setHaFetAllin(true);
            System.out.println("Fa Allin");
        }
        double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        boolean teDiners = jugador.getFitxesActuals() >= ((apostaMinima - quantitatAnterior) + dinersApostats);
        boolean esSuficient = apostaMinima < quantitatAnterior + dinersApostats;
        //if (teDiners && esSuficient) {
        apostar(jugador, (int) ((int) (apostaMinima + dinersApostats)), fase);
        fase.setApostaMinima((int) (apostaMinima + dinersApostats));
        System.out.println(jugador.getAlias() + " fa RAISE, puja: " + dinersApostats);
        //} else {
        //    System.out.println(jugador.getAlias() + " no pot aposstar RISE: " + (apostaMinima - quantitatAnterior) + " < " + dinersApostats);
        //}
    }

    public void repartirPremi(ArrayList<Jugador> jugadors, int pot) {
        if (jugadors.size() == 1) {
            jugadors.get(0).setFitxesActuals((jugadors.get(0).getFitxesActuals() + pot));
        } else if (jugadors.size() > 1) {
            //Pot2: po1 - fitxes apostades pels jugadors guanyadors
            int pot2 = pot;
            for (int i = 0; i < jugadors.size(); i++) {
                pot2 -= jugadors.get(i).getAposta().getQuantitat();
            }
            //Percentatge Total apostat pels jugadors guanyadors respecte el pot1
            int percentatgeTotal = 0;
            for (int i = 0; i < jugadors.size(); i++) {
                percentatgeTotal += jugadors.get(i).getAposta().getQuantitat() * pot / 100;
            }
            for (int i = 0; i < jugadors.size(); i++) {
                //Percentatge apostat pel jugador en el pot 1
                double percentatgeGuanys = jugadors.get(i).getAposta().getQuantitat() * pot / 100;
                //Percentatge guanys pel jugador en el pot 2
                double percentatgeGuanys2 = 100 * percentatgeGuanys / percentatgeTotal;
                int premi = (int) ((pot2 * percentatgeGuanys2 / 100) + jugadors.get(i).getAposta().getQuantitat());
                jugadors.get(i).setFitxesActuals(premi);
                System.out.println("El " + jugadors.get(i).getAlias() + " guanya: " + premi);
            }
        }
    }
}
