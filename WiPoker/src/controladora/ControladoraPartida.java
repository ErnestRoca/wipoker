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
    private ArrayList<Fase> fases = new ArrayList<Fase>();
    private ArrayList<Ronda> rondes = new ArrayList<Ronda>();

    public ControladoraPartida() {
        iniciarPartida();
    }

    public void iniciarPartida() {
        cd = new ControladoraDomini((byte) 10, new Baralla());
    }

    public void start() {
        if (cd.taulaIsFull() && cd.getPartida().getDataPartida() == null) {
            cd.assignarDataPartida();
            gestionarRonda();
        }
    }

    public void gestionarRonda() {
        Ronda novaRonda = new Ronda();
        rondes.add(novaRonda);
        barallar();
        cremarCartes();
        

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
