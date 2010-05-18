/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Aposta;
import domini.Baralla;
import domini.Carta;
import domini.Jugador;
import domini.Ronda;
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
            cartes.add(new Carta((byte) 0, i));
        }
        for (byte i = 2; i <= 14; i++) {
            cartes.add(new Carta((byte) 1, i));
        }
        for (byte i = 2; i <= 14; i++) {
            cartes.add(new Carta((byte) 2, i));
        }
        for (byte i = 2; i <= 14; i++) {
            cartes.add(new Carta((byte) 3, i));
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

                baralla.setCartesActuals((byte) (baralla.getCartesActuals() - 1));
            }

        }
        return privades;
    }

    public void aixecarCartes(ArrayList<Jugador> jugadors, Baralla baralla, byte numCartes) {
        for (int i = 0; i < numCartes; i++) {
            for (Jugador j : jugadors) {
                j.getMaActual().getCartes().add(baralla.getCartes().get(baralla.getCartesActuals()));

            }
            baralla.setCartesActuals((byte) (baralla.getCartesActuals() - 1));
        }
    }

    public void cremarCartes(Baralla baralla) {
        baralla.getCartes().remove(baralla.getCartes().size() - 1);
        baralla.setCartesActuals((byte) (baralla.getCartesActuals() - 1));
    }

    public void barallar(Baralla baralla) {
        Collections.shuffle(baralla.getCartes());
    }

    public void apostar(Jugador jugador, int quantitat, Ronda ronda) {
        //modificar cuando este hecha gui
        jugador.setAposta(new Aposta(jugador, quantitat));
        jugador.setFitxesActuals(jugador.getFitxesActuals() - quantitat);
        ronda.setPot(ronda.getPot() + quantitat);

    }

    public void repartirPremi(ArrayList<Jugador> jugadors, double pot) {
        pot = pot / jugadors.size();
        for (Jugador j : jugadors) {
            j.setFitxesActuals((int) (j.getFitxesActuals() + pot));
        }
    }
}
