                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domini;

import controladora.ControladoraIA;
import javax.swing.ImageIcon;

/**
 *
 * @author ula
 */
public class Bot extends Jugador {

    private ImageIcon avatar;

    public Bot(String alias, int fitxesInicials, int posicioTaula) {
        super(alias, fitxesInicials, posicioTaula, "bot");
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
    public String toString() {
        String str = "El bot " + getAlias() + " amb " + getFitxesActuals() + "fitxes";
        return str;
    }
}
