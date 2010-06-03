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

    public synchronized void ferFold(Jugador jugador, Fase fase) {
        jugador.setHaFetFold(true);
        apostar(jugador, 0, fase);
        System.out.println(jugador.getAlias() + " fa FOLD");
    }

    public synchronized void ferCall(Jugador jugador, Fase fase, int apostaMinima) {
        double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        boolean teDiners = jugador.getFitxesActuals() >= (apostaMinima - quantitatAnterior);
        if (teDiners) {
            apostar(jugador,(int) (apostaMinima), fase);
            fase.setApostaMinima(apostaMinima);
            System.out.println(jugador.getAlias() + " fa CALL");
        } else {
            System.out.println("no pot aposstar CALL");
        }
    }

    public synchronized void ferCheck(Jugador jugador, Fase fase, int apostaMinima) {
        //double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        double quantitatAnterior = jugador.getAposta().getQuantitat();
        if (quantitatAnterior == apostaMinima) {
            //no fa res. Ho hem fet volent
            fase.setApostaMinima(apostaMinima);
            apostar(jugador,(int) (apostaMinima), fase);
            System.out.println(jugador.getAlias() + " fa CHECK");
        }
    }

    public synchronized void ferRaise(Jugador jugador, Fase fase, int apostaMinima, int dinersApostats) {
        double quantitatAnterior = jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0;
        boolean teDiners = jugador.getFitxesActuals() >= (apostaMinima - quantitatAnterior + dinersApostats);
        boolean esSuficient = (apostaMinima - quantitatAnterior ) < dinersApostats;
        double dinersMinJug = -1;
        for (Jugador j: fase.getRonda().getPartida().getJugadors()) {
            if (j.getFitxesActuals() < dinersMinJug ||dinersMinJug < 0) {
                dinersMinJug = j.getFitxesActuals();
            }
        }
        if (teDiners && esSuficient) {
            if (dinersMinJug < dinersApostats) {
                dinersApostats = (int) dinersMinJug;
            }
            apostar(jugador,(int) (apostaMinima + dinersApostats), fase);
            fase.setApostaMinima((int) (apostaMinima + dinersApostats));
            System.out.println(jugador.getAlias() + " fa RAISE");
        } else {
            System.out.println("no pot aposstar RISE: " + (apostaMinima - quantitatAnterior ) + " < " + dinersApostats);
            System.out.println(jugador.getMaActual().getCartes());
        }
    }

    public void repartirPremi(ArrayList<Jugador> jugadors, int pot) {
        for (Jugador j : jugadors) {
            System.out.println("pot del jugador: " + j.getAlias() + ", " + j.getFitxesActuals());
        }
        for (Jugador j : jugadors) {
            j.setFitxesActuals((j.getFitxesActuals() + (pot / jugadors.size())));
        }
        for (Jugador j : jugadors) {
            System.out.println("NOU pot del jugador: " + j.getAlias() + ", " + j.getFitxesActuals());
        }
    }
}
