
/**
 * Bot.java
 */

package domini;

import controladora.ControladoraIA;

/**
 * Descripció de la classe concreta Persona.<br>
 * Modela un Bot(jugador automàtic) en el domini suggerit pels requeriments funcionals
 *
 * @author ula
 */
public class Bot extends Jugador {

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors
     * dels paràmetres als atributs<br>
     * 
     * @param alias
     * @param fitxesInicials
     * @param posicioTaula
     */
    public Bot(String alias, int fitxesInicials, int posicioTaula) {
        super(alias, fitxesInicials, posicioTaula, "bot");
    }

    /**
     * Determina què ha de fer el bot, quin tipus de jugada
     * @param ci
     * @param fase
     * @param numVegadesFase
     */
    public void jugadaBot(ControladoraIA ci, Fase fase, int numVegadesFase) {
        ci.calcularAposta(this, fase, numVegadesFase);

    }

    @Override
    public String toString() {
        String str = "El bot " + getAlias() + " amb " + getFitxesActuals() + "fitxes";
        return str;
    }
}
