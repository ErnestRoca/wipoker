/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domini;

import controladora.Torn;

/**
 *
 * @author ula
 */
public class Bot extends Jugador {
    private final  String alias;
    private int fitxesInicials;
    private int fitxesActuals;
    private final  byte posicioTaula;
    private Ma maActual;
    private Aposta aposta;
    private Torn torn;


    public Bot(String alias, int fitxesInicials) {
        super(alias);
        this.alias = alias;
        this.fitxesInicials = fitxesInicials;
        this.fitxesActuals = fitxesInicials;
        this.posicioTaula = 2;
    }

    public double jugadaBot() {

        return 0.0;
    }

    public Aposta getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }

    public int getFitxesActuals() {
        return fitxesActuals;
    }

    public void setFitxesActuals(int fitxesActuals) {
        this.fitxesActuals = fitxesActuals;
    }

    public int getFitxesInicials() {
        return fitxesInicials;
    }

    public void setFitxesInicials(int fitxesInicials) {
        this.fitxesInicials = fitxesInicials;
    }

    public Ma getMaActual() {
        return maActual;
    }

    public void setMaActual(Ma maActual) {
        this.maActual = maActual;
    }





}
