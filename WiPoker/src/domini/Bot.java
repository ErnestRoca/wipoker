                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domini;

import controladora.ControladoraIA;
import controladora.Torn;
import java.util.ArrayList;

/**
 *
 * @author ula
 */
public class Bot extends Jugador {

    private final String alias;
    private int fitxesInicials;
    private int fitxesActuals;
    private Ma maActual;

    public Bot(String alias, int fitxesInicials, int posicioTaula) {
        super(alias, fitxesInicials, posicioTaula);
        this.alias = alias;
        this.fitxesInicials = fitxesInicials;
        this.fitxesActuals = fitxesInicials;
        maActual = new Ma(new ArrayList<Carta>());
    }

    public void jugadaBot(ControladoraIA ci, Fase fase, int numVegadesFase) {
        ci.calcularAposta(this, fase, numVegadesFase);
    }

//    @Override
//    public Aposta getAposta() {
//        return aposta;
//    }
//
//    @Override
//    public void setAposta(Aposta aposta) {
//        this.aposta = aposta;
//    }

    @Override
    public int getFitxesActuals() {
        return fitxesActuals;
    }

    @Override
    public void setFitxesActuals(int fitxesActuals) {
        this.fitxesActuals = fitxesActuals;
    }

    @Override
    public int getFitxesInicials() {
        return fitxesInicials;
    }

    @Override
    public void setFitxesInicials(int fitxesInicials) {
        this.fitxesInicials = fitxesInicials;
    }

    @Override
    public Ma getMaActual() {
        return maActual;
    }

    @Override
    public void setMaActual(Ma maActual) {
        this.maActual = maActual;
    }

    @Override
    public String toString() {
        String str = "El bot " + alias + " amb " + fitxesActuals + "fitxes";
        return str;
    }
}
