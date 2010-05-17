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

    

    public ControladoraJoc()  {
        
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
        for (byte i = 21; i <= 14; i++) {
            cartes.add(new Carta((byte) 3, i));
        }
        Baralla b = new Baralla();
        b.setCartes(cartes);
        return b;
    }

    public void repartirCartesPrivades(ArrayList<Jugador> jugadors, Baralla baralla) {
        for (int i = 0; i <= 1; i++) {
            for (Jugador j : jugadors) {
                j.getMaActual().getCartes().add(baralla.getCartes().get(baralla.getCartesActuals()));
                baralla.setCartesActuals((byte) (baralla.getCartesActuals() - 1));
            }
        }
    }

    public void aixecarCartes(ArrayList<Jugador> jugadors, Baralla baralla, byte numCartes) {
        for (int i = 0; i < numCartes; i++) {
            for (Jugador j : jugadors) {
                j.getMaActual().getCartes().add(baralla.getCartes().get(baralla.getCartesActuals()));
                baralla.setCartesActuals((byte) (baralla.getCartesActuals() - 1));
            }
        }
    }

    public void cremarCartes(Baralla baralla) {
        baralla.getCartes().remove(baralla.getCartes().size());
    }

    public void barallar(Baralla baralla) {
        Collections.shuffle(baralla.getCartes());
    }

     public void apostar(Jugador jugador, int quantitat, Ronda ronda) {
        //modificar cuando este hecha gui
        int fase = ronda.getFases().size();
        if (quantitat > ronda.getFases().get(fase).getApostes().get(ronda.getFases().size()).getQuantitat()) {
            jugador.setAposta(new Aposta(jugador, quantitat));
            jugador.setFitxesActuals(jugador.getFitxesActuals() - quantitat);
            ronda.setPot(ronda.getPot() + quantitat);
        }
    }
}
