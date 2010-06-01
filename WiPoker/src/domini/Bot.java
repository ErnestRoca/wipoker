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
    private final byte posicioTaula;
    private Ma maActual;
    private Aposta aposta;
    private Torn torn;
    private boolean haFetFold = false;

    public Bot(String alias, int fitxesInicials) {
        super(alias, fitxesInicials);
        this.alias = alias;
        this.fitxesInicials = fitxesInicials;
        this.fitxesActuals = fitxesInicials;
        this.posicioTaula = 2;
        maActual = new Ma(new ArrayList<Carta>());
        torn = new Torn();
    }

    public void jugadaBot(ControladoraIA ci, Fase fase) {
        ci.calcularAposta(this, fase);
    }

    @Override
    public boolean isHaFetFold() {
        return haFetFold;
    }

    public void setHaFetFold(Boolean haFetFold) {
        this.haFetFold = haFetFold;
    }

    @Override
    public Aposta getAposta() {
        return aposta;
    }

    @Override
    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }

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
