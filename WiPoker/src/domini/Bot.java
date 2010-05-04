/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domini;

/**
 *
 * @author ula
 */
public class Bot {
    private final  String alias;
    private int fitxesInicials;
    private int fitxesActuals;
    private final  byte posicioTaula;
    private Ma maActual;
    private Aposta aposta;

    public Bot(String alias, int fitxesInicials, int fitxesActuals, byte posicioTaula) {
        this.alias = alias;
        this.fitxesInicials = fitxesInicials;
        this.fitxesActuals = fitxesActuals;
        this.posicioTaula = posicioTaula;
    }

    public double jugadaBot() {

        return 0.0;
    }



}
