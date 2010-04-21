/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Baralla;
import domini.Carta;
import domini.Fase;
import domini.Jugador;
import domini.Partida;
import domini.Ronda;
import domini.Taula;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartida {

    private Taula taula;
    private Baralla baralla;
    private Partida partida;
    private ArrayList<Jugador> jugadors;
    
    

    public ControladoraPartida(byte maxJugadors) {
        baralla = new Baralla();
        crearBaralla();
        taula = new Taula(maxJugadors, baralla);
        partida = new Partida(Calendar.getInstance());
        taula.setPartidaActual(partida);
        jugadors = partida.getJugadors();
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
    }

    public boolean taulaIsFull() {
        boolean completa = taula.getPlaces() - taula.getCadiresOcupades() == 0;
        return completa;
    }

    public void afegirJugador(Jugador nouJugador) {
        if (!taulaIsFull()) {
            jugadors.add(nouJugador);
            taula.setCadiresOcupades((byte) (taula.getCadiresOcupades() + 1));
        } else {
            //implementar metodo no quedanPlazas
        }
    }    

    public void iniciarRonda() {
        Ronda novaRonda = new Ronda(0);
        partida.getRondes().add(novaRonda);
        barallar();
        //cremarCartes(); //preguntar a Oleguer
        //no es crema cap carta, si ho entenc be avanç s'han de donar les cartes
        //als jugadors, i aqui no es crema cap carta, mireu el gmail.

        //S'ha de fer un metode repartirCartesJugadors() o algu aixi
    }

    public void gestionarFase(Ronda ronda) {
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
