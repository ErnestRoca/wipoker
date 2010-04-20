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
import java.util.TimeZone;

/**
 *
 * @author andres
 */
public class ControladoraAndres {

    public double ferAposta(double diners) {
        Aposta novaAposta = new Aposta(diners);
        return novaAposta.getQuantitat();
    }

    public void assignarDataPartida(Taula taula) {
        Calendar c = Calendar.getInstance();
        String data = Calendar.YEAR + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH;
        taula.setPartidaActual(new Partida(c));
    }

   public void barallar(Baralla b) {
       b.barallar();
   }

}
