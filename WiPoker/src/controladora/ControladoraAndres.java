/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Aposta;
import domini.Baralla;
import domini.Jugador;
import domini.Partida;
import domini.Taula;
import java.util.Calendar;

/**
 *
 * @author andres
 */
public class ControladoraAndres {

    /* Mètode que assigna la data (d'inici de la partida en estar la taula completa
     * Paràmetres: Taula l'objecte taula en que es jugara la partida
     * Retorna: void
     */
    public void assignarDataPartida(Taula taula) {
        Calendar c = Calendar.getInstance();
        String data = Calendar.YEAR + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH;
        
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
}


