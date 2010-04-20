/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Aposta;
import domini.Baralla;
import domini.Partida;
import domini.Taula;
import java.util.Calendar;

/**
 *
 * @author andres
 */
public class ControladoraAndres {

    public double ferAposta(double diners) {
        Aposta novaAposta = new Aposta(diners);
        return novaAposta.getQuantitat();
    }

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
}
