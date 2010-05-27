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
        Aposta aposta = new Aposta(jugador, quantitat);
        jugador.setAposta(aposta);
        jugador.setFitxesActuals(jugador.getFitxesActuals() - quantitat);
        fase.getApostes().add(aposta);
        fase.getRonda().setPot(fase.getRonda().getPot() + quantitat);
    }

    public synchronized void ferFold(Jugador jugador) {
        jugador.setHaFetFold(true);
    }

    public synchronized void ferCall(Jugador jugador, Fase fase, int apostaMinima) {
        double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        boolean teDiners = jugador.getFitxesActuals() >= (apostaMinima - quantitatAnterior);
        if (teDiners) {
            apostar(jugador,(int) (apostaMinima - quantitatAnterior), fase);
            System.out.println("igualada l'aposta minima +" + (apostaMinima - quantitatAnterior));
        } else {
            System.out.println("no pot aposstar");
        }
    }

    public synchronized void ferCheck(Jugador jugador, Fase fase, int apostaMinima) {
        double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        if (quantitatAnterior == apostaMinima) {
            //no fa res. Ho hem fet volent
        }
    }

    public synchronized void ferRaise(Jugador jugador, Fase fase, int apostaMinima, int dinersApostats) {
        double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        System.out.println("quantitat anterior = " + quantitatAnterior);
        System.out.println("aposta min" + apostaMinima);
        boolean teDiners = jugador.getFitxesActuals() >= (apostaMinima - quantitatAnterior + dinersApostats);
        boolean esSuficient = (apostaMinima - quantitatAnterior ) < dinersApostats;
        if (teDiners && esSuficient) {
            apostar(jugador,(int) (apostaMinima - quantitatAnterior + dinersApostats), fase);
            System.out.println("aposta de " + (apostaMinima - quantitatAnterior + dinersApostats));
            fase.setApostaMinima((int) (apostaMinima - quantitatAnterior  + dinersApostats));
        } else {
            System.out.println("no pot aposstar");
        }
    }

    public void repartirPremi(ArrayList<Jugador> jugadors, int pot) {
        for (Jugador j : jugadors) {
            j.setFitxesActuals((j.getFitxesActuals() + (pot / jugadors.size())));
        }
    }
}
