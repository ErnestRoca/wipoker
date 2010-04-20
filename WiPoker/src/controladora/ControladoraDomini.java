/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora;

import domini.Aposta;
import domini.Baralla;
import domini.Fase;
import domini.Jugador;
import domini.Partida;
import domini.Ronda;
import domini.Taula;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author wida45787385
 */
public class ControladoraDomini {
    
    private Taula taula;
    private Partida partida;
    private ArrayList<Jugador> jugadors;
    
    public ControladoraDomini(byte places, Baralla baralla) {
        taula = new Taula(places, baralla);
        partida = new Partida(null);
        jugadors = new ArrayList<Jugador>();
        partida.setJugadors(jugadors);

    }



    /* Mètode que assigna la data (d'inici de la partida en estar la taula completa
     * Paràmetres: Taula l'objecte taula en que es jugara la partida
     * Retorna: void
     */
    public void assignarDataPartida(Taula taula) {
        Calendar c = Calendar.getInstance();
        //String data = Calendar.YEAR + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH;
        taula.setPartidaActual(new Partida(c));
    }

    /* Mètode que remena les cartes de la baralla que s'esta emprant a la partida
     * Paràmetres: Baralla l'objecte baralla assignat a la taula
     * Retorna: void no
     */
    public void barallar(Baralla b) {
        b.barallar();
    }

    /* Mètode que controla que quedin places per afegir-se a la partida
     * Paràmetres: Taula l'objecte taula en que es juga la partida
     * Retorna: boolean true si la taula està al complert
     */
    public boolean taulaIsFull(Taula taula) {
        boolean completa = taula.getPlaces() - taula.getCadiresOcupades() == 0;
        return completa;
    }

    /* Mètode que controla les repercussions d'un jugador que fa fold
     * Paràmetres: Jugador: El jugador que fa fold
     * Retorna: void
     */
    public void ferFold(Jugador jugador) {
        ferAposta(jugador, 0);
    }

    /* Mètode que controla les repercussions d'un jugador que fa una acció aposta
     * Paràmetres: Jugador: El jugador que fa l'aposta
     * Paràmetres: diners de al'aposta
     * Retorna: double diners apostats
     */
    public double ferAposta(Jugador jugador, double diners) {
        Aposta novaAposta = new Aposta(diners);
        return novaAposta.getQuantitat();
    }

    /* Mètode que afegeix un jugador a la partida
     * Paràmetres: Jugador: El jugador que entra en la partida
     * Paràmetres: Taula: la taula a la que es juga la partida
     * Retorna: boolean true si entra a la partida diners apostats
     */
    public boolean entrarPartida(Taula taula, Jugador jugador) {
        if (!taulaIsFull(taula)) {
            taula.getPartidaActual().getJugadors().add(jugador);
            return true;
        } else {
            return false;
        }
    }

    /* Mètode que retira un jugador a la partida
     * Paràmetres: Jugador: El jugador que entra en la partida
     * Paràmetres: Taula: la taula a la que es juga la partida
     * Retorna: void
     */
    public void sortirPartida(Taula taula, Jugador jugador) {
        taula.getPartidaActual().getJugadors().remove(jugador);
    }

    //Afageix jugador
    private void addJugador(Jugador j) {
        partida.getJugadors().add(j);
    }

    //Elimina jugador
    private void removeJugador(Jugador j) {
        partida.getJugadors().remove(j);
    }

    //Afageix ronda
    private void addRonda(Ronda r) {
        partida.getRondes().add(r);
    }

    //Elimina ronda
    private void removeRonda(Ronda r) {
        partida.getRondes().remove(r);
    }

    //Esatabelix el guanyador de la partida
    private void estabelixGuanyadorPartida(Jugador j) {
        partida.setJugadorGuanyador(j);
    }

    //private void estableixDataPartida(Calendar c)   No se si cal posar-ho

    //Afageix fase
    private void addFase(Fase f, byte numRonda) {
        partida.getRondes().get(numRonda).getFases().add(f);
    }

    //Elimina fase
    private void removeFase(Fase f, byte numRonda) {
        partida.getRondes().get(numRonda).getFases().remove(f);
    }

    //Estableix el guanyador de la ronda
    private void estabelixGuanyadorRonda(Jugador j, byte numRonda) {
        partida.getRondes().get(numRonda).setJugadorGuanyador(j);
    }

    //Estableix el pot de la ronda
    private void estabelixPotRonda(double pot, byte numRonda) {
        partida.getRondes().get(numRonda).setPot(pot);
    }


}

