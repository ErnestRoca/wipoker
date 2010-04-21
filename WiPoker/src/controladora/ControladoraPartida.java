/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Aposta;
import domini.Baralla;
import domini.Carta;
import domini.Fase;
import domini.Ronda;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartida {

    private ControladoraDomini cd;
    

    public ControladoraPartida(byte maxJugadors) {
        iniciarPartida(maxJugadors);
    }

    public void iniciarPartida(byte jugadors) {
        cd = new ControladoraDomini(jugadors, new Baralla());
    }

    public void crearBaralla() {
        ArrayList<Carta> cartes = new ArrayList<Carta>();
        for (byte i = 1; i <=13; i++) {
            cartes.add(new Carta((byte) 0,i));
        }
        for (byte i = 1; i <=13; i++) {
            cartes.add(new Carta((byte) 1,i));
        }
        for (byte i = 1; i <=13; i++) {
            cartes.add(new Carta((byte) 2,i));
        }
        for (byte i = 1; i <=13; i++) {
            cartes.add(new Carta((byte) 3,i));
        }
        cd.getTaula().getBaralla().setCartes(cartes);
    }

    public void start() {
        if (cd.taulaIsFull() && cd.getPartida().getDataPartida() == null) {
            cd.assignarDataPartida();
            gestionarRonda();
        }
    }

    public void gestionarRonda() {
        Ronda novaRonda = new Ronda();        
        barallar();
        cremarCartes();
        //gestionarFase();
        //Afegir potRonda al pot general
    }

    public void gestionarFase(Aposta aposta, Fase fase) {
        //Clase fase te dos static: array string nom fases i byte amb el numero de fase
        //Passem al constructor l'string de l'index de la fase
        Fase novaFase = new Fase(Fase.getFases()[Fase.getNumFase()]);
        if (Fase.getNumFase() == 1) {
            aixecarCartes((byte) 3);
        } else if (Fase.getNumFase() > 1) {
            aixecarCartes((byte) 1);
        } else if (Fase.getNumFase() == 3) {
            afegirAlPot(aposta.getQuantitat(), novaFase);
            Fase.setNumFase((byte) 0);
        }

        //Al finalitzar la fase afegir potFase al pot de la ronda
    }

    public ArrayList<Carta> aixecarCartes(byte numCartes) {
        cremarCartes();
        ArrayList<Carta> cartesRetornades = new ArrayList<Carta>();
        ArrayList<Carta> cartesBaralla = cd.getTaula().getBaralla().getCartes();
        for (int i = cartesBaralla.size(); cartesRetornades.size() < numCartes; i--) {
            cartesRetornades.add(cartesBaralla.get(i));
            cartesBaralla.remove(i);
        }
        return cartesRetornades;
    }

    public void cremarCartes() {
        ArrayList<Carta> cartes = cd.getTaula().getBaralla().getCartes();
        cartes.remove(cartes.size());
    }

    public void barallar() {
        Collections.shuffle(cd.getTaula().getBaralla().getCartes());
    }

    public void afegirAlPot(double aposta, Fase fase) {
    }
}
